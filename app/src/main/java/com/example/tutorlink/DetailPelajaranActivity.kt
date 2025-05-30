package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tutorlink.databinding.ActivityDetailPelajaranBinding
import adapters.TutorSpesialisAdapter
import com.example.tutorlink.FirebaseHelper.database
import model.AllTutor
import model.KategoriPelajaran
import model.Tutor

class DetailPelajaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPelajaranBinding
    private lateinit var tutorAdapter: TutorSpesialisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPelajaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kategoriNama = intent.getStringExtra("nama_pelajaran") ?: return


        // Ambil detail pelajaran dari Firebase
        database.child("KategoriPelajaran").child(kategoriNama)
            .get()
            .addOnSuccessListener { snapshot ->
                val kategori = snapshot.getValue(KategoriPelajaran::class.java)
                if (kategori != null) {
                    binding.txtNamaPelajaran.text = kategori.nama
                    binding.txtDeskripsi.text = kategori.deskripsi
                    Glide.with(this).load(kategori.imageUrl).into(binding.imgPelajaran)
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Gagal memuat detail pelajaran", Toast.LENGTH_SHORT).show()
            }

        // Ambil data tutor sesuai pelajaran
        FirebaseHelper.getAllTutors(
            onSuccess = { list ->
                val filteredTutors = list.filter { it.pelajaran == kategoriNama }
                val allTutorList = filteredTutors.map {
                    AllTutor(
                        nama = it.nama,
                        pelajaran = it.pelajaran,
                        lokasi = it.lokasi,
                        biaya = it.biaya,
                        jarak = it.jarak,
                        deskripsi = it.deskripsi,
                        rating = it.rating.toString(),
                        imageUrl = it.imageUrl // ← pastikan field ini ada di model Tutor
                    )
                }


                // Setup adapter
                tutorAdapter = TutorSpesialisAdapter(allTutorList) { tutor ->
                    val intent = Intent(this, ProfilTutorActivity::class.java).apply {
                        putExtra("namaTutor", tutor.nama)
                        putExtra("spesialisTutor", tutor.pelajaran)
                        putExtra("biayaTutor", tutor.biaya)
                        putExtra("deskripsiTutor", tutor.deskripsi)
                        putExtra("fotoTutor", tutor.imageUrl) // ✅ diganti dari imageResId ke imageUrl
                    }
                    startActivity(intent)
                }


                binding.recyclerTutorSpesialis.apply {
                    layoutManager = LinearLayoutManager(this@DetailPelajaranActivity)
                    adapter = tutorAdapter
                }
            },
            onFailure = { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        )

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
