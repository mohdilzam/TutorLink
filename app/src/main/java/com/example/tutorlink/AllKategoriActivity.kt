package com.example.tutorlink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import model.KategoriPelajaran
import adapters.KategoriAdapter
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager


class AllKategoriActivity : AppCompatActivity() {

    private lateinit var recyclerAllKategori: RecyclerView
    private val kategoriList = listOf(
        KategoriPelajaran("Kimia", R.drawable.kimia),
        KategoriPelajaran("Fisika", R.drawable.kimia),
        KategoriPelajaran("Biologi", R.drawable.kimia),
        // ... Tambahkan sesuai data kamu
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_kategori)

        recyclerAllKategori = findViewById(R.id.recyclerAllKategori)
        recyclerAllKategori.layoutManager = GridLayoutManager(this, 3)
        recyclerAllKategori.adapter = KategoriAdapter(kategoriList) { kategori ->
            val intent = Intent(this, DetailPelajaranActivity::class.java).apply {
                putExtra("nama_pelajaran", kategori.nama)
                putExtra("icon_pelajaran", kategori.imageResId)
            }
            startActivity(intent)
        }


        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}
