package com.example.i_portal

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

@Suppress("UNUSED_ANONYMOUS_PARAMETER", "DEPRECATION")
class AdminDashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //user login state
    private var isLoggedIn = true

    //initialize all layer
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admindashboard)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)

        //toolbar initialization
        val toolbar= findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //navigation view initialization
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        //toggle
        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null)
        {
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.navigation_home)
        }
    }

    //fragments
    private fun replaceFragment(fragment: Fragment)
    {
        val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        //nav view toggle button
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        //log out back button
        else if (isLoggedIn) {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.home-> replaceFragment(HomeFragment())
            R.id.logout-> showSignOutDialog()
        }
        return true
    }

}