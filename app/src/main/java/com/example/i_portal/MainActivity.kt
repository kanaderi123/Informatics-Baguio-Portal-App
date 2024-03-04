package com.example.i_portal


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.i_portal.AdminLogin
import com.example.i_portal.R
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        // Go to student login
        val studButton = findViewById<Button>(R.id.btn_student)
        studButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, StudentLogin::class.java)
            startActivity(intent)
        })

        // Go to admin login
        val adButton = findViewById<Button>(R.id.btn_admin)
        adButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
        })
    }
}
