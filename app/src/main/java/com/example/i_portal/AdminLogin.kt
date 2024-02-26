package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.i_portal.databinding.ActivityAdminLoginBinding


class AdminLogin : AppCompatActivity() {

    //admin user
    private lateinit var binding: ActivityAdminLoginBinding
    private var adminuname = "admin"
    private var adminpword = "info0515"

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

            //condition to check if username and password are empty
/*            if(tbuname.isEmpty() || tbpword.isEmpty())
            {
                Toast.makeText(this@AdminLogin, "Please Input your correct Username and Password", Toast.LENGTH_SHORT).show()
            }
            //condition to check if username and password are correct
            else if(tbuname == adminuname && tbpword == adminpword)
            {
                //If true Go to admin dashboard
*/
                val intent = Intent(this,AdminDashboard::class.java)
                startActivity(intent)
/*
                Toast.makeText(this@AdminLogin, "Login Successful", Toast.LENGTH_SHORT).show()
                finish()
            }
            //if false, pop up text alert
            else
            {
                Toast.makeText(this@AdminLogin, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
*/
        }
    }


}