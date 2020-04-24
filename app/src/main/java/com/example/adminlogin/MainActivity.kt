package com.example.adminlogin

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

        tLayanan.setOnClickListener {
            startActivity(Intent(this, LayananActivity::class.java))
            finish()
        }

        tAntrian.setOnClickListener {
            startActivity(Intent(this, AntrianActivity::class.java))
            finish()
        }
    }
}
