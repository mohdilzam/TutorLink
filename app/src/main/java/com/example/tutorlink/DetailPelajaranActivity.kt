package com.example.tutorlink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import adapters.TutorSpesialisAdapter
import android.content.Intent
import android.widget.ImageView
import com.example.tutorlink.databinding.ActivityDetailPelajaranBinding
import model.AllTutor

//class DetailPelajaranActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityDetailPelajaranBinding
//    private lateinit var tutorAdapter: TutorSpesialisAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Inisialisasi binding
//        binding = ActivityDetailPelajaranBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val namaPelajaran = intent.getStringExtra("namaPelajaran")
//        binding.txtNamaPelajaran.text = namaPelajaran ?: "Pelajaran"
//
//        val dummyTutors = listOf(
//            AllTutor("Kak Yusuf", "Kimia", "Banda Aceh", 3, 4.5, R.drawable.tutor),
//            AllTutor("Kak Lisa", "Kimia", "Lhokseumawe", 7, 4.9, R.drawable.tutor)
//        )
//
//        tutorAdapter = TutorSpesialisAdapter(dummyTutors) { tutor ->
//            // Tindakan saat klik "Lihat Profil"
//        }
//
//        // Gunakan binding untuk akses RecyclerView
//        binding.recyclerTutorSpesialis.apply {
//            layoutManager = LinearLayoutManager(this@DetailPelajaranActivity)
//            adapter = tutorAdapter
//        }
//
//        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
//        }
//    }
//}

class DetailPelajaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPelajaranBinding
    private lateinit var tutorAdapter: TutorSpesialisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPelajaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent
        val namaPelajaran = intent.getStringExtra("nama_pelajaran") ?: "Pelajaran"
        val iconPelajaran = intent.getIntExtra("icon_pelajaran", R.drawable.kimia)

        // Tampilkan nama dan icon pelajaran
        binding.txtNamaPelajaran.text = namaPelajaran
        binding.imgPelajaran.setImageResource(iconPelajaran)

        // Data dummy tutor spesialis sesuai pelajaran
        val dummyTutors = listOf(
            AllTutor(
                nama = "Kak Yusuf",
                pelajaran = namaPelajaran,
                lokasi = "Banda Aceh",
                biaya = "Rp50.000",
                jarak = 3,
                deskripsi = "Berpengalaman mengajar Kimia selama 5 tahun",
                rating = "4.5",
                imageResId = R.drawable.tutor
            ),
            AllTutor(
                nama = "Kak Lisa",
                pelajaran = namaPelajaran,
                lokasi = "Lhokseumawe",
                biaya = "Rp60.000",
                jarak = 7,
                deskripsi = "Ahli Kimia lulusan S2 UNIMED",
                rating = "4.9",
                imageResId = R.drawable.tutor
            )
        )

        // Setup adapter dan RecyclerView
        tutorAdapter = TutorSpesialisAdapter(dummyTutors) { tutor ->
            val intent = Intent(this, ProfilTutorActivity::class.java)
            intent.putExtra("namaTutor", tutor.nama)
            intent.putExtra("spesialisTutor", tutor.pelajaran)
            intent.putExtra("biayaTutor", tutor.biaya)
            intent.putExtra("deskripsiTutor", tutor.deskripsi)
            intent.putExtra("fotoTutor", tutor.imageResId)
            startActivity(intent)
        }

        binding.recyclerTutorSpesialis.apply {
            layoutManager = LinearLayoutManager(this@DetailPelajaranActivity)
            adapter = tutorAdapter
        }

        // Tombol kembali
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}