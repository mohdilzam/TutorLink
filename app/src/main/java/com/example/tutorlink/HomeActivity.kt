//package com.example.tutorlink
//
//import adapters.BannerAdapter
//import adapters.KategoriAdapter
//import adapters.TutorAdapter
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.ViewPager2
//import model.KategoriPelajaran
//import model.Tutor
//import com.example.tutorlink.R
//
//class HomeActivity : AppCompatActivity() {
//
//    private val kategoriList = listOf(
//        KategoriPelajaran("Kimia", R.drawable.kimia),
//        KategoriPelajaran("Fisika", R.drawable.kimia),
//        KategoriPelajaran("Matematika", R.drawable.kimia),
//        KategoriPelajaran("B. Inggris", R.drawable.kimia)
//    )
//
//    private val tutorList = listOf(
//        Tutor("Kak Rizky", "Biologi", 4.7, R.drawable.kimia),
//        Tutor("Kak Lisa", "Fisika", 4.8, R.drawable.kimia),
//        Tutor("Kak Raja", "Matematika", 4.9, R.drawable.kimia)
//    )
//
//    private val bannerList = listOf(
//        R.drawable.kimia,
//        R.drawable.kimia,
//        R.drawable.kimia
//    )
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//
//        val viewPagerHero = findViewById<ViewPager2>(R.id.viewPagerHero)
//        viewPagerHero.adapter = BannerAdapter(bannerList)
//
//        val recyclerKategori = findViewById<RecyclerView>(R.id.recyclerKategori)
//        recyclerKategori.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerKategori.adapter = KategoriAdapter(kategoriList) { selectedKategori ->
//            val intent = Intent(this, DetailPelajaranActivity::class.java)
//            intent.putExtra("namaPelajaran", selectedKategori.nama)
//            startActivity(intent)
//        }
//
//
//        val recyclerTutor = findViewById<RecyclerView>(R.id.recyclerTutor)
//        recyclerTutor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerTutor.adapter = TutorAdapter(tutorList)
//
//        val btnShowAllKategori = findViewById<TextView>(R.id.btnShowAllKategori)
//        btnShowAllKategori.setOnClickListener {
//            val intent = Intent(this, AllKategoriActivity::class.java)
//            startActivity(intent)
//        }
//
//        val btnShowAllTutor = findViewById<TextView>(R.id.btnShowAllTutor)
//        btnShowAllTutor.setOnClickListener {
//            val intent = Intent(this, AllTutorsActivity::class.java)
//            startActivity(intent)
//        }
//    }
//}

package com.example.tutorlink

import adapters.BannerAdapter
import adapters.KategoriAdapter
import adapters.TutorAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import model.KategoriPelajaran
import model.Tutor

class HomeActivity : AppCompatActivity() {

    private val kategoriList = listOf(
        KategoriPelajaran("Kimia", R.drawable.kimia),
        KategoriPelajaran("Fisika", R.drawable.kimia),
        KategoriPelajaran("Matematika", R.drawable.kimia),
        KategoriPelajaran("B. Inggris", R.drawable.kimia)
    )

    private val tutorList = listOf(
        Tutor("Kak Rizky", "Biologi", 4.7, R.drawable.kimia),
        Tutor("Kak Lisa", "Fisika", 4.8, R.drawable.kimia),
        Tutor("Kak Raja", "Matematika", 4.9, R.drawable.kimia)
    )

    private val bannerList = listOf(
        R.drawable.kimia,
        R.drawable.kimia,
        R.drawable.kimia
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewPagerHero = findViewById<ViewPager2>(R.id.viewPagerHero)
        viewPagerHero.adapter = BannerAdapter(bannerList)

        val recyclerKategori = findViewById<RecyclerView>(R.id.recyclerKategori)
        recyclerKategori.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerKategori.adapter = KategoriAdapter(kategoriList) { selectedKategori ->
            val intent = Intent(this, DetailPelajaranActivity::class.java)
            intent.putExtra("namaPelajaran", selectedKategori.nama)
            startActivity(intent)
        }

        val recyclerTutor = findViewById<RecyclerView>(R.id.recyclerTutor)
        recyclerTutor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerTutor.adapter = TutorAdapter(tutorList)

        val btnShowAllKategori = findViewById<TextView>(R.id.btnShowAllKategori)
        btnShowAllKategori.setOnClickListener {
            val intent = Intent(this, AllKategoriActivity::class.java)
            startActivity(intent)
        }

        val btnShowAllTutor = findViewById<TextView>(R.id.btnShowAllTutor)
        btnShowAllTutor.setOnClickListener {
            val intent = Intent(this, AllTutorsActivity::class.java)
            startActivity(intent)
        }

        // === Bottom Navigation ===
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // sudah di halaman ini
                R.id.nav_book -> {
                    startActivity(Intent(this, BookedTutorActivity::class.java))
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, PesanActivity::class.java))
                    true
                }
                 R.id.nav_profile -> {
                     startActivity(Intent(this, ProfilActivity::class.java))
                     true
                 }
                else -> false
            }
        }
    }
}
