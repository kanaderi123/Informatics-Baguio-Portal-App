package com.example.i_portal

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AdminRegistration : AppCompatActivity() {

    private lateinit var tbIdNumber: TextInputEditText
    private lateinit var tbUsername: TextInputEditText
    private lateinit var tbFirstName: TextInputEditText
    private lateinit var tbMidName: TextInputEditText
    private lateinit var tbLastName: TextInputEditText
    private lateinit var tbCourseName: TextInputEditText
    private lateinit var tbPassword: TextInputEditText
    private lateinit var btnRegister: Button

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_registration)
        FirebaseApp.initializeApp(this)

        tbIdNumber = findViewById(R.id.tb_IdNumber)
        tbUsername = findViewById(R.id.tb_Username)
        tbFirstName = findViewById(R.id.tb_Firstname)
        tbMidName = findViewById(R.id.tb_Midname)
        tbLastName = findViewById(R.id.tb_Lastname)
        tbCourseName = findViewById(R.id.tb_Coursename)
        tbPassword = findViewById(R.id.tb_pword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val idNumber = tbIdNumber.text.toString().trim()
        val username = tbUsername.text.toString().trim()
        val firstName = tbFirstName.text.toString().trim()
        val midName = tbMidName.text.toString().trim()
        val lastName = tbLastName.text.toString().trim()
        val courseName = tbCourseName.text.toString().trim()
        val password = tbPassword.text.toString().trim()

        if (idNumber.isEmpty() || username.isEmpty() || firstName.isEmpty() ||
            midName.isEmpty() || lastName.isEmpty() || courseName.isEmpty() || password.isEmpty()
        ) {
            showToast("All fields are required.")
            return
        }

        // Validate the email address format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            showToast("Invalid email address format.")
            return
        }

        // Check if the email address ends with "@gmail.com"
        if (!username.endsWith("@gmail.com", ignoreCase = true)) {
            showToast("Please use a Gmail address.")
            return
        }

        // Register user with Firebase Authentication
        auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // User registration successful, store additional details in Realtime Database
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val user = mapOf(
                            "idNumber" to idNumber,
                            "username" to username,
                            "firstName" to firstName,
                            "midName" to midName,
                            "lastName" to lastName,
                            "courseName" to courseName
                            // Do not include "password" in the map
                        )

                        // Save user details to Realtime Database
                        database.reference.child("users").child(userId).setValue(user)
                            .addOnSuccessListener {
                                showToast("Registration successful.")
                                finish()
                            }
                            .addOnFailureListener { e ->
                                showToast("Error storing user data: ${e.message}")
                            }
                    }
                } else {
                    showToast("Registration failed: ${task.exception?.message}")
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
