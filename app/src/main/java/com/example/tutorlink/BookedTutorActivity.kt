package com.example.tutorlink

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class BookedTutorActivity : AppCompatActivity() {

    private lateinit var tabBooked: TextView
    private lateinit var tabSelesai: TextView
    private lateinit var layoutBooked: LinearLayout
    private lateinit var layoutSelesai: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked_tutor)

        // Bind views
        tabBooked = findViewById(R.id.tabBooked)
        tabSelesai = findViewById(R.id.tabSelesai)
        layoutBooked = findViewById(R.id.layoutBooked)
        layoutSelesai = findViewById(R.id.layoutSelesai)

        val btnHubungi = findViewById<ImageView>(R.id.btnHubungi)
        val btnGantiJadwal = findViewById<Button>(R.id.btnGantiJadwal)
        val btnBatalkan = findViewById<Button>(R.id.btnBatalkan)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set tab default
        val tabIntent = intent.getStringExtra("tab")
        if (tabIntent == "selesai") {
            switchToSelesai()
        } else {
            switchToBooked()
        }

        // Tab clicked
        tabBooked.setOnClickListener {
            switchToBooked()
        }

        tabSelesai.setOnClickListener {
            switchToSelesai()
        }

        btnHubungi.setOnClickListener {
            val intent = Intent(this, RoomChatActivity::class.java)
            // Kirim data tutor jika perlu
            intent.putExtra("namaTutor", "Kak Yusuf")
            startActivity(intent)
        }

        // Tombol ganti jadwal
        btnGantiJadwal.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                val timePicker = TimePickerDialog(this, { _, hour, minute ->
                    val selectedTime = String.format("%02d:%02d", hour, minute)
                    Toast.makeText(this, "Jadwal diubah ke: $selectedDate - $selectedTime", Toast.LENGTH_LONG).show()
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                timePicker.show()
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        // Tombol batalkan
        btnBatalkan.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Batalkan Tutor")
                .setMessage("Apakah kamu yakin ingin membatalkan sesi tutor ini?")
                .setPositiveButton("Ya") { _, _ ->
                    Toast.makeText(this, "Tutor berhasil dibatalkan", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        // Tombol review
        btnReview.setOnClickListener {
            val intent = Intent(this, ReviewTutorActivity::class.java)
            startActivity(intent)
        }

        // Bottom navigation
        bottomNav.selectedItemId = R.id.nav_book
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfilActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, PesanActivity::class.java))
                    true
                }
                R.id.nav_book -> true
                else -> false
            }
        }
    }

    private fun switchToBooked() {
        layoutBooked.visibility = View.VISIBLE
        layoutSelesai.visibility = View.GONE
        tabBooked.setBackgroundResource(R.drawable.tab_selected)
        tabSelesai.setBackgroundColor(Color.TRANSPARENT)
        tabBooked.setTextColor(Color.parseColor("#38CBBD"))
        tabSelesai.setTextColor(Color.parseColor("#888888"))
    }

    private fun switchToSelesai() {
        layoutBooked.visibility = View.GONE
        layoutSelesai.visibility = View.VISIBLE
        tabSelesai.setBackgroundResource(R.drawable.tab_selected)
        tabBooked.setBackgroundColor(Color.TRANSPARENT)
        tabSelesai.setTextColor(Color.parseColor("#38CBBD"))
        tabBooked.setTextColor(Color.parseColor("#888888"))
    }
}
