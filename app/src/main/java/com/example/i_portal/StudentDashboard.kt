package com.example.i_portal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudentDashboard : AppCompatActivity() {

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

    private fun replaceFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


}
