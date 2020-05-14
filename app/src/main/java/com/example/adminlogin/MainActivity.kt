package com.example.adminlogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tModel.setOnClickListener {
            startActivity(Intent(this, ModelActivity::class.java))
            finish()
        }

        taModel.setOnClickListener{
            startActivity(Intent(this, Model2Activity::class.java))
            finish()
        }

        tLayanan.setOnClickListener {
            startActivity(Intent(this, LayananActivity::class.java))
            finish()
        }

        taLayanan.setOnClickListener {
            startActivity(Intent(this, Layanan2Activity::class.java))
            finish()
        }

        tAntrian.setOnClickListener {
            startActivity(Intent(this, AntrianActivity::class.java))
            finish()
        }

        tLogout.setOnClickListener {
            val sharedPreferences=getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
            val editor=sharedPreferences.edit()

            editor.putString("STATUS","0")
            editor.apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
