package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.i_portal.databinding.ActivityAdminLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener

class AdminLogin : AppCompatActivity() {

    private lateinit var binding: ActivityAdminLoginBinding

    // Reference to Firebase Realtime Database
    private lateinit var database: FirebaseDatabase
    private lateinit var dataRef: DatabaseReference

    // Variables to hold the data
    private var adminUsers: Map<String, AdminUser>? = null

    // Textboxes
    private lateinit var tbuname: TextInputEditText
    private lateinit var tbpword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        dataRef = database.getReference("adminUsers")

        // Back button
        binding.btnback.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        // Bind textboxes
        tbuname = findViewById(R.id.tb_uname)
        tbpword = findViewById(R.id.tb_pword)

        // Attach an event listener to get data updates
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get the data from the snapshot as a map
                adminUsers = dataSnapshot.getValue(object : GenericTypeIndicator<Map<String, AdminUser>>() {})

                // Handle data retrieval
                if (adminUsers == null || adminUsers?.isEmpty() == true) {
                    // Handle the case when no admin users are found
                    adminUsers = emptyMap()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                runOnUiThread {
                    println("Database error: $error")
                }
            }
        })

        // Login button
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val enteredUsername = tbuname.text.toString()
            val enteredPassword = tbpword.text.toString()

            // Conditions
            if (isValidLogin(enteredUsername, enteredPassword)) {
                val intent = Intent(this, AdminDashboard::class.java)
                startActivity(intent)
                Toast.makeText(this@AdminLogin, "Login Successful", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@AdminLogin, "Login Failed, Check your credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Data class to represent admin user
    data class AdminUser(
        val adminuname: String? = "",
        val adminpword: String? = ""
    )

    private fun isValidLogin(username: String, password: String): Boolean {
        // Hardcoded username and password for validation
        return username == "admin" && password == "info"
    }
}
