package com.example.bitfit_pt1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bitfit_pt1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(LogsFragment())

        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_log -> replaceFragment(LogsFragment())
                R.id.nav_dash -> replaceFragment(DashboardFragment())
                // Add other cases as needed

                else -> {

                }
            }

            true
        }

        binding.addMealButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment) // Use the correct container ID
        fragmentTransaction.commit()
    }
}
