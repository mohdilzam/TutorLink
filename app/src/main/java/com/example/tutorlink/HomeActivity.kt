package com.example.tutorlink

import adapters.KategoriAdapter
import adapters.TutorAdapter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import model.KategoriPelajaran
import model.Tutor

class HomeActivity : AppCompatActivity() {

    private lateinit var kategoriRecycler: RecyclerView
    private lateinit var tutorRecycler: RecyclerView
    private lateinit var database: FirebaseDatabase
    private lateinit var kategoriAdapter: KategoriAdapter
    private lateinit var tutorAdapter: TutorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        database = FirebaseDatabase.getInstance()

        kategoriRecycler = findViewById(R.id.recyclerKategori)
        tutorRecycler = findViewById(R.id.recyclerTutor)

        kategoriRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tutorRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Load data dari Firebase
        loadKategoriPelajaran()
        loadTutor()
    }

    private fun loadKategoriPelajaran() {
        val kategoriRef = database.getReference("kategori_pelajaran")


        kategoriRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val kategoriList = mutableListOf<KategoriPelajaran>()
                for (data in snapshot.children) {
                    val kategori = data.getValue(KategoriPelajaran::class.java)
                    if (kategori != null) {
                        kategoriList.add(kategori)
                    }
                }
                kategoriAdapter = KategoriAdapter(kategoriList) {
                    Toast.makeText(this@HomeActivity, "Klik: ${it.nama}", Toast.LENGTH_SHORT).show()
                }
                kategoriRecycler.adapter = kategoriAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeActivity, "Gagal load kategori", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadTutor() {
        val tutorRef = database.getReference("tutor")

        tutorRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tutorList = mutableListOf<Tutor>()
                for (data in snapshot.children) {
                    val tutor = data.getValue(Tutor::class.java)
                    if (tutor != null) {
                        tutorList.add(tutor)
                    }
                }
                tutorAdapter = TutorAdapter(tutorList)
                tutorRecycler.adapter = tutorAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeActivity, "Gagal load tutor", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
