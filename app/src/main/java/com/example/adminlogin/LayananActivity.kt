package com.example.adminlogin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_layanan.*
import org.json.JSONArray
import org.json.JSONObject

class LayananActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan)

        val context=this

        val recyclerView2 = findViewById(R.id.recyclerView2) as RecyclerView
        recyclerView2.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val layanan=ArrayList<Layanan>()

        AndroidNetworking.get("http://192.168.100.14/cukuran/layanan.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTittle", jsonObject.optString("nama"))

                        val isi1 = jsonObject.optString("nama").toString()
                        val isi2 = jsonObject.optString("harga").toString()
                        val isi3 = jsonObject.optString("keterangan").toString()

                        layanan.add(Layanan("$isi1", "$isi3", "$isi2"))

                    }

                    val adapter = LayananAdapter(layanan)
                    recyclerView2.adapter= adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })

        btnBack2.setOnClickListener {
            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}