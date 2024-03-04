package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.i_portal.databinding.ActivityStudentLoginBinding




class StudentLogin : AppCompatActivity()
{

    private lateinit var binding: ActivityStudentLoginBinding
    //test user initialization
    private var uname = "user123"
    private var pword = "123"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)


        binding = ActivityStudentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button
        binding.btnback.setOnClickListener{
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        //login button
        binding.btnLogin.setOnClickListener{
            //bind textbox via ID to a variable
            val tbuname = binding.tbStudid.text.toString()
            val tbpword = binding.tbPword.text.toString()



            //condition to check if username and password are empty
            if(tbuname.isEmpty() || tbpword.isEmpty())
            {
                Toast.makeText(this@StudentLogin, "Please Input your ID Number and Password", Toast.LENGTH_SHORT).show()
            }
            //condition to check if username and password are correct
            else if(tbuname == uname && tbpword == pword)
            {
                //If true Go to student login
                    val intent = Intent(this,StudentDashboard::class.java)
                    startActivity(intent)
                    Toast.makeText(this@StudentLogin, "Login Successful", Toast.LENGTH_SHORT).show()
                    finish()
            }
            //if false, pop up text alert
            else
            {
                Toast.makeText(this@StudentLogin, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
        }

    }

}

