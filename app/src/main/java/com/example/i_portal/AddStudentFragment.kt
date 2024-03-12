package com.example.i_portal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddStudentFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        // Initialize Firebase
        val database = FirebaseDatabase.getInstance()
        databaseReference = database.reference

        // Find the "Save Changes" button
        val btnSaveChanges: Button = view.findViewById(R.id.btn_save_changes)

        btnSaveChanges.setOnClickListener {
            addStudentToFirebase(view)
        }

        return view
    }

    private fun addStudentToFirebase(view: View) {
        // Retrieve input values from your TextInputEditText fields
        val idNumber = view.findViewById<TextInputEditText>(R.id.Idnumber).text.toString()
        val lastName = view.findViewById<TextInputEditText>(R.id.IdLastname).text.toString()
        val firstName = view.findViewById<TextInputEditText>(R.id.IdFirstname).text.toString()
        val middleName = view.findViewById<TextInputEditText>(R.id.IdMiddleName).text.toString()
        val password = view.findViewById<TextInputEditText>(R.id.IdPassword).text.toString()
        val course = view.findViewById<TextInputEditText>(R.id.IdCourse).text.toString()

        // Validate input (add your own validation logic)

        // Generate a unique key for the new student
        val studentKey = databaseReference.child("students").push().key

        // If a key was generated, proceed to update the database
        studentKey?.let {
            val studentData = mapOf(
                "idNumber" to idNumber,
                "lastName" to lastName,
                "firstName" to firstName,
                "middleName" to middleName,
                "password" to password,
                "course" to course
            )

            // Use a transaction to ensure atomic updates
            databaseReference.child("students").child(it).setValue(studentData)
                .addOnSuccessListener {
                    // Transaction was successful
                    Toast.makeText(requireContext(), "Student added successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Transaction failed
                    Toast.makeText(requireContext(), "Failed to add student", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
