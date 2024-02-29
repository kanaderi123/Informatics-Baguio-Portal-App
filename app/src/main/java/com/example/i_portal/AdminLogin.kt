package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.i_portal.databinding.ActivityAdminLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AdminLogin : AppCompatActivity() {

    //admin user
    private lateinit var binding: ActivityAdminLoginBinding

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        databaseReference = FirebaseDatabase.getInstance().reference.child("adminUsers")


        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        //back button
        binding.btnback.setOnClickListener{
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        binding.btnLogin.setOnClickListener{

            // Read data from the database
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Retrieve data
                        val data = dataSnapshot.getValue(User::class.java)
                        //bind textbox via ID to a variable
                        val tbuname = binding.tbUname.text.toString()
                        val tbpword = binding.tbPword.text.toString()

                        if(tbuname.isEmpty() || tbpword.isEmpty())
                        {
                            Toast.makeText(this@AdminLogin, "Please Input your Username and Password", Toast.LENGTH_SHORT).show()
                        }
                        else if(data?.adminuname != tbuname || data.adminpword != tbpword)
                        {
                            Toast.makeText(this@AdminLogin, "Login Failed, Check your credentials", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            loginSuccess()
                            Toast.makeText(this@AdminLogin, "Login Successful", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle errors
                    Log.e("Firebase", "Failed to read value.", error.toException())
                }
            })
        }

        }

    private fun loginSuccess() {
        val intent = Intent(this,AdminDashboard::class.java)
        startActivity(intent)
        finish()
    }

    data class User(
                val adminuname:String?="",
                val adminpword:String?=""
            )

    }


