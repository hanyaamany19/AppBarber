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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan)

        getdariserver()

        btnTmbh.setOnClickListener() {
            val data_nama :String = textnama.text.toString()
            val data_harga :String = textharga.text.toString()
            val data_keterangan :String = textketerangan.text.toString()

            postCreate(data_nama, data_harga, data_keterangan)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnEdit.setOnClickListener() {
            val data_nama1 :String = textnama.text.toString()
            val data_harga1 :String = textharga.text.toString()
            val data_keterangan1 :String = textketerangan.text.toString()

            postUpdate(data_nama1, data_harga1, data_keterangan1)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener(){
            val data_nama1 :String = textnama.text.toString()

            postDelete(data_nama1)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("WrongConstant")
    fun getdariserver(){
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val layanan=ArrayList<Layanan>()

        AndroidNetworking.get("https://cukuran.000webhostapp.com/dt_layanan.php")
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

                        layanan.add(Layanan("$isi1", "$isi2", "$isi3"))

                    }

                    val adapter = LayananAdapter(layanan)
                    recyclerView.adapter= adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postCreate(nama:String, harga:String, keterangan:String){
        AndroidNetworking.post("https://cukuran.000webhostapp.com/create_layanan.php")
            .addBodyParameter("nama", nama)
            .addBodyParameter("harga", harga)
            .addBodyParameter("keterangan", keterangan)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }

    fun postUpdate(nama:String, harga:String, keterangan:String){
        AndroidNetworking.post("https://cukuran.000webhostapp.com/update_layanan.php")
            .addBodyParameter("nama", nama)
            .addBodyParameter("harga", harga)
            .addBodyParameter("keterangan", keterangan)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }

    fun postDelete(nama:String){
        AndroidNetworking.post("https://cukuran.000webhostapp.com/delete_layanan.php")
            .addBodyParameter("nama", nama)
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
