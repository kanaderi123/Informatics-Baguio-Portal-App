package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.i_portal.databinding.ActivityStudentLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class StudentLogin : AppCompatActivity()
{

    private lateinit var binding: ActivityStudentLoginBinding

    // Reference to Firebase Realtime Database
    private lateinit var dataRef: DatabaseReference

    // variable to hold the data
    private var data1: String? = null
    private var data2: String? = null

    //textbox
    private lateinit var tbidnum: TextInputEditText
    private lateinit var tbpword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)


        binding = ActivityStudentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        dataRef = FirebaseDatabase.getInstance().getReference("User UID")


        //back button
        binding.btnback.setOnClickListener{
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        //bind
        tbidnum = findViewById(R.id.tb_studid)
        tbpword = findViewById(R.id.tb_pword)

        // Attach an event listener to get data updates
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Get the data from the snapshot
                data1 = snapshot.getValue(String::class.java)

                //bind
               // data1 = data?.idNumber
               // data2 = data?.password

                println(data1)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                println("Database error: $error")
            }
        })

        //login button
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener{
            val ui1 = tbidnum.text.toString()
            val ui2 = tbpword.text.toString()

            println(ui1)
            //conditions
            if(ui1 == data1 && ui2 == data2)
            {
                val intent = Intent(this,StudentDashboard::class.java)
                startActivity(intent)
                Toast.makeText(this@StudentLogin, "Login Successful", Toast.LENGTH_SHORT).show()
                finish()
            }
            else
            {
                Toast.makeText(this@StudentLogin, "Login Failed, Check your credentials", Toast.LENGTH_SHORT).show()
            }
        }


    }
    data class StudentUser(
        val idNumber:String?="",
        val password:String?=""
    )
}


