package com.example.tutorlink

import BookingAdapter
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorlink.FirebaseHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import model.Booking

class BookedTutorActivity : AppCompatActivity() {

    private lateinit var tabBooked: TextView
    private lateinit var tabSelesai: TextView
    private lateinit var recyclerBooking: RecyclerView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var bookingQuery: Query
    private val bookedList = mutableListOf<Booking>()
    private val selesaiList = mutableListOf<Booking>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked_tutor)

        tabBooked = findViewById(R.id.tabBooked)
        tabSelesai = findViewById(R.id.tabSelesai)
        recyclerBooking = findViewById(R.id.recyclerBooking)
        bottomNav = findViewById(R.id.bottomNavigationView)

        recyclerBooking.layoutManager = LinearLayoutManager(this)

        val userId = FirebaseHelper.getCurrentUserId()
        if (userId == null) {
            Toast.makeText(this, "User belum login", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        bookingQuery = FirebaseDatabase.getInstance()
            .getReference("Bookings")
            .orderByChild("userId")
            .equalTo(userId)

        tabBooked.setOnClickListener { showBooked() }
        tabSelesai.setOnClickListener { showSelesai() }

        bottomNav.selectedItemId = R.id.nav_book
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }

                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfilActivity::class.java))
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

        loadBookings()
    }

    private fun loadBookings() {
        bookingQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookedList.clear()
                selesaiList.clear()
                for (data in snapshot.children) {
                    val booking = data.getValue(Booking::class.java)
                    booking?.bookingId = data.key // <- Tambahan penting ini

                    if (booking != null) {
                        if (booking.status == "booked") {
                            bookedList.add(booking)
                        } else if (booking.status == "selesai") {
                            selesaiList.add(booking)
                        }
                    }
                }
                showBooked()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BookedTutorActivity, "Gagal memuat data", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


    private fun showBooked() {
        recyclerBooking.adapter = BookingAdapter(bookedList)
        tabBooked.setBackgroundResource(R.drawable.tab_selected)
        tabSelesai.setBackgroundColor(Color.TRANSPARENT)
        tabBooked.setTextColor(Color.parseColor("#38CBBD"))
        tabSelesai.setTextColor(Color.parseColor("#888888"))
    }

    private fun showSelesai() {
        recyclerBooking.adapter = BookingAdapter(selesaiList)
        tabSelesai.setBackgroundResource(R.drawable.tab_selected)
        tabBooked.setBackgroundColor(Color.TRANSPARENT)
        tabSelesai.setTextColor(Color.parseColor("#38CBBD"))
        tabBooked.setTextColor(Color.parseColor("#888888"))
    }
}
