package com.example.finalprojectgg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectgg.ui.fragments.HomeFragment
import com.example.finalprojectgg.ui.fragments.ViewPagerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityXml : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // ✅ Load HomeFragment when app starts
        if (savedInstanceState == null) {  // Ensure it's only loaded once
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // Handle Bottom Navigation Clicks
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_viewpager -> {
                    loadFragment(ViewPagerFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
