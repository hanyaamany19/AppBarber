package com.example.adminlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_model2.*
import org.json.JSONArray

class Model2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model2)

        btnTmbh11.setOnClickListener() {
            val data_nama :String = txtNama.text.toString()

            postCreate(data_nama)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnBack12.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun postCreate(nama:String){
        AndroidNetworking.post("http://192.168.100.14/cukuran/insert_model.php")
            .addBodyParameter("name", nama)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }}
