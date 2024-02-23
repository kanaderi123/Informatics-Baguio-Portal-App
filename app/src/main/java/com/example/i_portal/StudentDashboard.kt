package com.example.i_portal

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudentDashboard : AppCompatActivity() {

    //user login state
    private var isLoggedIn = true

    //initialization of pages
    private val homeFragment = HomeFragment()
    private val schedFragment = SchedFragment()
    private val gradesFragment = GradesFragment()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId)
            {
                R.id.navigation_home -> replaceFragment(homeFragment)
                R.id.navigation_schedule -> replaceFragment(schedFragment)
                R.id.navigation_grades -> replaceFragment(gradesFragment)
            }
            true
        }
        // Set the default fragment
        replaceFragment(homeFragment)
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

    private fun replaceFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
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
