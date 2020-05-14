package com.example.adminlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_layanan2.*
import org.json.JSONArray

class Layanan2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan2)

        btnTmbh.setOnClickListener() {
            val data_nama :String = textnama.text.toString()
            val data_harga :String = textharga.text.toString()
            val data_keterangan :String = textketerangan.text.toString()

            postCreate(data_nama, data_harga, data_keterangan)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun postCreate(nama1:String, harga1:String, keterangan1:String){
        AndroidNetworking.post("http://192.168.100.14/cukuran/insert_layanan.php")
            .addBodyParameter("nama", nama1)
            .addBodyParameter("harga", harga1)
            .addBodyParameter("keterangan", keterangan1)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }
}
