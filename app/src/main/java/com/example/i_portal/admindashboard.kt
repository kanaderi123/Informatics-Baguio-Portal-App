package com.example.i_portal

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

@Suppress("UNUSED_ANONYMOUS_PARAMETER", "DEPRECATION")
class AdminDashboard : AppCompatActivity() {

    //user login state
    private var isLoggedIn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admindashboard)


    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (isLoggedIn) {
            // If logged in, ask for sign out or close the app
            showSignOutDialog()
        } else {
            // If not logged in, proceed with the default back button behavior
            super.onBackPressed()
        }
    }

    private fun showSignOutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sign Out")
            .setMessage("Do you want to sign out?")
            .setPositiveButton("Sign Out") { dialog, which ->
                // Perform sign out actions
                // Reset isLoggedIn to false
                isLoggedIn = false
                // Proceed with default back button behavior
                super.onBackPressed()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Close the dialog and stay in the current activity
                dialog.dismiss()
            }
            .show()
    }

}