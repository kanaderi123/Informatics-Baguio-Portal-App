package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        //Go to student login
        val studButton = findViewById<Button>(R.id.btn_login)
        studButton.setOnClickListener(
            View.OnClickListener
        {
            val intent = Intent(this,admindashboard::class.java)
            startActivity(intent)
        })
    }
}