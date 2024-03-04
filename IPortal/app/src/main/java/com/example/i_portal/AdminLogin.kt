package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.i_portal.databinding.ActivityAdminLoginBinding


class AdminLogin : AppCompatActivity() {

    //admin user
    private lateinit var binding: ActivityAdminLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button
        binding.btnback.setOnClickListener{
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        binding.btnLogin.setOnClickListener{
            //bind textbox via ID to a variable
            val tbuname = binding.tbUname.text.toString()
            val tbpword = binding.tbPword.text.toString()



                val intent = Intent(this,AdminDashboard::class.java)
                startActivity(intent)

                Toast.makeText(this@AdminLogin, "Login Successful", Toast.LENGTH_SHORT).show()
                finish()


        }
    }




}