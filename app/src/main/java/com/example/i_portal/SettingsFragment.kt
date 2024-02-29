package com.example.i_portal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SettingsFragment : Fragment() {

    //initialization
    private lateinit var data1 : TextInputEditText
    private lateinit var data2 : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        //bind from textbox to variable
        data1 = view.findViewById(R.id.tb_adminUsername)
        data2 = view.findViewById(R.id.tb_adminPassword)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase
        val database =FirebaseDatabase.getInstance()
        val dbref = database.reference.child("adminUsers")

        // Read data from the database
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve data
                    val data = dataSnapshot.getValue(AdminUser::class.java)

                    // Update the TextInputEditText with the retrieved data
                    data1.setText(data?.adminuname)
                    data2.setText(data?.adminpword)

                    //bind button to variable
                    val btnsave = view.findViewById<Button>(R.id.btn_save_changes)


                    //update button
                    btnsave.setOnClickListener{
                        val updateduname = data1.text.toString()
                        val updatedpword = data2.text.toString()

                        //if no changes
                        if(data?.adminuname != updateduname || data.adminpword != updatedpword)
                        {
                            dbref.child("adminuname").setValue(updateduname)
                            dbref.child("adminpword").setValue(updatedpword)

                            showToast("Update Success")
                        }
                        //if there is changes
                        else
                        {
                            // Data is the same, show a message
                            showToast("Data is already up to date")
                        }
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to read value.", error.toException())
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    data class AdminUser(
        val adminuname:String?="",
        val adminpword:String?=""
    )
}



