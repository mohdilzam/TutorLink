package com.example.tutorlink

import adapters.KategoriAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.KategoriPelajaran
import com.example.tutorlink.FirebaseHelper
import androidx.appcompat.widget.SearchView



class AllKategoriActivity : AppCompatActivity() {

    private lateinit var recyclerAllKategori: RecyclerView
    private lateinit var kategoriAdapter: KategoriAdapter
    private val kategoriList = mutableListOf<KategoriPelajaran>()
    private lateinit var searchViewKategori: SearchView
    private var originalList = listOf<KategoriPelajaran>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_kategori)

        recyclerAllKategori = findViewById(R.id.recyclerAllKategori)
        recyclerAllKategori.layoutManager = GridLayoutManager(this, 3)

        kategoriAdapter = KategoriAdapter(kategoriList) { kategori ->
            val intent = Intent(this, DetailPelajaranActivity::class.java).apply {
                putExtra("kategoriNama", kategori.nama)
            }
            startActivity(intent)
        }
        recyclerAllKategori.adapter = kategoriAdapter

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Inisialisasi SearchView
        val searchViewKategori = findViewById<SearchView>(R.id.searchViewKategori)
        searchViewKategori.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false // tidak perlu aksi khusus saat submit
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = originalList.filter {
                    it.nama.contains(newText ?: "", ignoreCase = true)
                }
                kategoriList.clear()
                kategoriList.addAll(filtered)
                kategoriAdapter.notifyDataSetChanged()
                return true
            }
        })

        loadKategoriPelajaran()
    }


    private fun loadKategoriPelajaran() {
        FirebaseHelper.getAllKategoriPelajaran(
            onSuccess = { list ->
                originalList = list
                kategoriList.clear()
                kategoriList.addAll(list)
                kategoriAdapter.notifyDataSetChanged()
            },
            onFailure = { error ->
                Toast.makeText(this, "Gagal: $error", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
