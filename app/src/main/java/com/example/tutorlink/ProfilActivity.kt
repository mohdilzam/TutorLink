package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val btnEditProfil = findViewById<Button>(R.id.btnEditProfil)
        val tvLogout = findViewById<TextView>(R.id.tvLogout)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        btnEditProfil.setOnClickListener {
            startActivity(Intent(this, EditProfilActivity::class.java))
        }

        tvLogout.setOnClickListener {
            Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
             startActivity(Intent(this, LoginActivity::class.java))
             finish()
        }

        // Setup Bottom Navigation
        bottomNav.selectedItemId = R.id.nav_profile
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_book -> {
                    startActivity(Intent(this, BookedTutorActivity::class.java))
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, PesanActivity::class.java))
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }
}
