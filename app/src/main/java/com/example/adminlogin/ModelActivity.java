package com.example.adminlogin;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class ModelActivity extends AppCompatActivity {
    final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    EditText nameEditText;
    private ImageView teacherImageView;
    private Button showChooserBtn,sendToMySQLBtn;
    private ProgressBar uploadProgressBar;

    class Model{
        private String name,imageURL;
        public Model(String name) {
            this.name = name;
            this.imageURL = imageURL;
        }
        public String getName() {return name;}
    }

    public class MyUploader{
        private static final String DATA_UPLOAD_URL="http://192.168.100.14/barber/index.php";

        private final Context c;
        public MyUploader(Context c) {this.c = c;}
        /*
        SAVE/INSERT
         */
        public void upload(Model s, final View...inputViews)
        {
            if(s == null){Toast.makeText(c, "No Data To Save", Toast.LENGTH_SHORT).show();}
            else {
                File imageFile;
                try {
                    imageFile = new File(getImagePath(filePath));

                }catch (Exception e){
                    Toast.makeText(c, "Please pick an Image From Right Place, maybe Gallery or File Explorer so that we can get its path."+e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                uploadProgressBar.setVisibility(View.VISIBLE);

                AndroidNetworking.upload(DATA_UPLOAD_URL)
                        .addMultipartFile("image",imageFile)
                        .addMultipartParameter("teacher_name",s.getName())
                        .addMultipartParameter("name","upload")
                        .setTag("MYSQL_UPLOAD")
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if(response != null) {
                                    try{
                                        //SHOW RESPONSE FROM SERVER
                                        String responseString = response.get("message").toString();
                                        Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_LONG).show();

                                        if (responseString.equalsIgnoreCase("Success")) {
                                            //RESET VIEWS
                                            EditText nameEditText = (EditText) inputViews[0];
                                            EditText descriptionEditText = (EditText) inputViews[1];
                                            ImageView teacherImageView = (ImageView) inputViews[2];

                                            nameEditText.setText("");
                                            descriptionEditText.setText("");
                                            teacherImageView.setImageResource(R.drawable.placeholder);

                                        } else {
                                            Toast.makeText(c, "PHP WASN'T SUCCESSFUL. ", Toast.LENGTH_LONG).show();
                                        }
                                    }catch(Exception e)
                                    {
                                        e.printStackTrace();
                                        Toast.makeText(c, "JSONException "+e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(c, "NULL RESPONSE. ", Toast.LENGTH_LONG).show();
                                }
                                uploadProgressBar.setVisibility(View.GONE);
                            }
                            @Override
                            public void onError(ANError error) {
                                error.printStackTrace();
                                uploadProgressBar.setVisibility(View.GONE);
                                Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : \n"+error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image To Upload"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                teacherImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImagePath(Uri uri)
    {
        String[] projection={MediaStore.Images.Media.DATA};
        Cursor cursor=getContentResolver().query(uri,projection,null,null,null);
        if(cursor == null){
            return null;
        }
        int columnIndex= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(columnIndex);
        cursor.close();
        return s;
    }
    private boolean validateData()
    {
        String name=nameEditText.getText().toString();
        if(filePath == null){return false;}

        return true;
    }

    /*
    OnCreate method. When activity is created
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        nameEditText=findViewById(R.id.nameEditText);
        showChooserBtn=findViewById(R.id.chooseBtn);
        sendToMySQLBtn=findViewById(R.id.sendBtn);
        Button openActivityBtn=findViewById(R.id.openActivityBtn);
        teacherImageView=findViewById(R.id.imageView);
        uploadProgressBar=findViewById(R.id.myProgressBar);

        showChooserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();

            }
        });

        sendToMySQLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    //GET VALUES
                    String teacher_name = nameEditText.getText().toString();

                    Model s = new Model(teacher_name);

                    //upload data to mysql
                    new MyUploader(ModelActivity.this).upload(s, nameEditText, teacherImageView);
                } else {
                    Toast.makeText(ModelActivity.this, "PLEASE ENTER ALL FIELDS CORRECTLY ", Toast.LENGTH_LONG).show();
                }
            }
        });

        openActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ModelActivity.this,SplashActivity.class);
                startActivity(intent);
            }
        });
    }
}
