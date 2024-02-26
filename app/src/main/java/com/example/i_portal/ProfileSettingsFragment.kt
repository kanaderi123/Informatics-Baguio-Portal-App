package com.example.i_portal

// Import necessary Firebase libraries
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener




// Initialize Firebase
val database = FirebaseDatabase.getInstance()


class ProfileSettingsFragment : Fragment() {

    // Reference to the 'adminusers' node in the database
    private val usersRef = database.getReference("adminusers")




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_profile_settings, container, false)

        val tbuser = rootView.findViewById<TextInputEditText>(R.id.tb_adminUsername)
        val  tbpword = rootView.findViewById<TextInputEditText>(R.id.tb_adminPassword)
        val userId = "userId"


        // Attach a listener to read the data
        usersRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get user data
                val user = dataSnapshot.getValue(AdminUsers::class.java)

                // Update the TextView with the user data
                tbuser.setText(user?.uname)
                tbpword.setText(user?.pword)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
                println("Error: ${databaseError.message}")
            }
        })
        return rootView
    }


}

data class AdminUsers(
    val uname: String? = "",
    val pword: String? = ""
)