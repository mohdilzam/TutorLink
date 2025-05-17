package com.example.tutorlink

import adapters.AllTutorAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.AllTutor


class AllTutorsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllTutorAdapter
    private lateinit var tutorList: List<AllTutor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tutor)

        recyclerView = findViewById(R.id.recyclerAllTutor)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Tombol back ke HomeActivity
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Dummy data
        tutorList = listOf(
            AllTutor("Kak Yusuf", "Tutor Kimia", "Lokasi, Banda Aceh", "Rp50.000", 3, "Berpengalaman mengajar 5 tahun", "5.0", R.drawable.tutor),
            AllTutor("Kak Raja", "Tutor Matematika", "Lokasi, Banda Aceh", "Rp60.000", 2, "Spesialis UTBK dan olimpiade", "4.8", R.drawable.tutor),
            AllTutor("Kak Fatimah", "Tutor B. Inggris", "Lokasi, Banda Aceh", "Rp55.000", 4, "Lulusan S2 luar negeri", "4.9", R.drawable.tutor),
            AllTutor("Kak Lisa", "Tutor Fisika", "Lokasi, Banda Aceh", "Rp50.000", 5, "Pengalaman mengajar 3 tahun", "4.7", R.drawable.tutor),
            AllTutor("Kak Bara", "Tutor Olahraga", "Lokasi, Banda Aceh", "Rp45.000", 1, "Tutor profesional dan ramah", "5.0", R.drawable.tutor)
        )

        adapter = AllTutorAdapter(
            tutorList,
            onItemClick = { tutor ->
                val intent = Intent(this, ProfilTutorActivity::class.java).apply {
                    putExtra("namaTutor", tutor.nama)
                    putExtra("spesialisTutor", tutor.pelajaran)
                    putExtra("biayaTutor", tutor.biaya)
                    putExtra("deskripsiTutor", tutor.deskripsi)
                    putExtra("fotoTutor", tutor.imageResId)
                }
                startActivity(intent)
            },
            onChatClick = { tutor ->
                val intent = Intent(this, RoomChatActivity::class.java).apply {
                    putExtra("namaTutor", tutor.nama)
                }
                startActivity(intent)
            }
        )

        recyclerView.adapter = adapter
    }
}