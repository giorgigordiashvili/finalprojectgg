package com.example.finalprojectgg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finalprojectgg.ui.theme.FinalProjectGGTheme

import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectgg.ui.fragments.HomeFragment
import com.example.finalprojectgg.ui.fragments.ViewPagerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityXml : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the activity's layout defined in XML
        setContentView(R.layout.activity_main)

        // Set up the BottomNavigationView to switch between fragments
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.nav_viewpager -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ViewPagerFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
