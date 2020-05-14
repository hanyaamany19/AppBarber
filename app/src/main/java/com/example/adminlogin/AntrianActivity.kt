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
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_antrian.*
import kotlinx.android.synthetic.main.activity_layanan.*
import org.json.JSONObject

class AntrianActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antrian)

        val context=this

        val recyclerView3 = findViewById(R.id.recyclerView3) as RecyclerView
        recyclerView3.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users=ArrayList<Antrian>()

        AndroidNetworking.get("http://192.168.100.14/cukuran/antrian.php")
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
                        val isi2 = jsonObject.optString("no_antrian").toString()
                        val isi3 = jsonObject.optString("model").toString()
                        val isi4 = jsonObject.optString("layanan").toString()
                        val isi5 = jsonObject.optString("time").toString()

                        users.add(Antrian("$isi1", "$isi2", "$isi3", "$isi4", "$isi5"))

                    }

                    val adapter = AntrianAdapter(users)
                    recyclerView3.adapter= adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })

        btnBack6.setOnClickListener {
            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
