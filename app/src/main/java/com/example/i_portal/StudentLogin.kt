package com.example.i_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// ... (previous imports)

class StudentLogin : AppCompatActivity() {

    private lateinit var tbStudId: EditText
    private lateinit var tbPword: EditText
    private lateinit var btnLogin: Button
    private lateinit var saveLoginInfo: CheckBox
    private lateinit var btnBack: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize UI elements
        tbStudId = findViewById(R.id.tb_studid)
        tbPword = findViewById(R.id.tb_pword)
        btnLogin = findViewById(R.id.btn_login)
        saveLoginInfo = findViewById(R.id.save_login_info)
        btnBack = findViewById(R.id.btnback)

        btnLogin.setOnClickListener {
            // Get user input
            val idNumber = tbStudId.text.toString()
            val password = tbPword.text.toString()

            if (idNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both ID number and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Authenticate user with ID number as the email address
            signInWithIdNumberAndPassword(idNumber, password)
        }

        btnBack.setOnClickListener {
            // Handle the "Back" button click to go back to the previous activity
            finish()
        }
    }

    private fun signInWithIdNumberAndPassword(idNumber: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword("$idNumber@example.com", password)
            .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    // Authentication successful
                    val user: FirebaseUser? = firebaseAuth.currentUser
                    // Save login info to Realtime Database with the IdNumber
                    saveLoginInfoToDatabase(user?.uid, idNumber)

                    // Navigate to StudentDashboard
                    val intent = Intent(this, StudentDashboard::class.java)
                    startActivity(intent)
                    finish() // Close the current login activity
                } else {
                    // Log additional information for debugging
                    task.exception?.printStackTrace()

                    // If authentication fails, display a more specific message.
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "User not found. Please check your ID number."
                        is FirebaseAuthInvalidCredentialsException -> "Invalid Username or Password. Please try again."
                        else -> "Authentication Failed"
                    }

                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun saveLoginInfoToDatabase(userId: String?, idNumber: String) {
        // Implement saving login information to Realtime Database
        // You can use FirebaseDatabase.getInstance().getReference() to get a reference to your database
        // For example:
        val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId!!)

        // Update the IdNumber in the database
        databaseReference.child("IdNumber").setValue(idNumber)

        // Retrieve additional data based on idNumber
        retrieveAdditionalData(idNumber)
    }

    private fun retrieveAdditionalData(idNumber: String) {
        // ... (rest of the code)
    }
}
