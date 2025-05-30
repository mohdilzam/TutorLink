package com.example.tutorlink

import adapters.ReviewAdapter
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.Review
import android.content.Intent
import com.bumptech.glide.Glide

class ProfilTutorActivity : AppCompatActivity() {

    private lateinit var imgTutor: ImageView
    private lateinit var txtNamaTutor: TextView
    private lateinit var txtSpesialisTutor: TextView
    private lateinit var txtBiayaTutor: TextView
    private lateinit var txtDeskripsi: TextView
    private lateinit var recyclerReview: RecyclerView
    private lateinit var btnBack: ImageView
    private lateinit var btnBooking: Button
    private lateinit var btnHubungi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_tutor)

        // Inisialisasi View
        imgTutor = findViewById(R.id.imgTutor)
        txtNamaTutor = findViewById(R.id.txtNamaTutor)
        txtSpesialisTutor = findViewById(R.id.txtSpesialisTutor)
        txtBiayaTutor = findViewById(R.id.txtBiayaTutor)
        txtDeskripsi = findViewById(R.id.txtDeskripsi)
        recyclerReview = findViewById(R.id.recyclerReview)
        btnBack = findViewById(R.id.btnBack)
        btnBooking = findViewById(R.id.btnBooking)
        btnHubungi = findViewById(R.id.btnHubungi)

        // Tangkap data dari intent
        val namaTutor = intent.getStringExtra("namaTutor")
        val spesialis = intent.getStringExtra("spesialisTutor")
        val biaya = intent.getStringExtra("biayaTutor")
        val deskripsi = intent.getStringExtra("deskripsiTutor")
        val imageUrl = intent.getStringExtra("imageUrl")

        // Tampilkan data
        txtNamaTutor.text = namaTutor
        txtSpesialisTutor.text = spesialis
        txtBiayaTutor.text = biaya
        txtDeskripsi.text = deskripsi

        // Tampilkan gambar menggunakan Glide
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.tutor)
                .error(R.drawable.tutor)
                .into(imgTutor)
        } else {
            imgTutor.setImageResource(R.drawable.tutor)
        }

        // Tombol kembali
        btnBack.setOnClickListener { finish() }

        // Booking tutor
        btnBooking.setOnClickListener {
            val intent = Intent(this, BookingActivity::class.java)
            startActivity(intent)
        }

        // Hubungi tutor
        btnHubungi.setOnClickListener {
            val intent = Intent(this, RoomChatActivity::class.java)
            startActivity(intent)
        }

        // Dummy data review
        val reviewList = listOf(
            Review("Andi", "Tutor sangat ramah dan penjelasannya mudah dimengerti.", 5f, "2 hari lalu", R.drawable.tutor),
            Review("Sinta", "Materi disampaikan dengan jelas dan sabar.", 4.5f, "1 minggu lalu", R.drawable.tutor),
            Review("Budi", "Pengajaran interaktif dan menyenangkan.", 5f, "3 minggu lalu", R.drawable.tutor),
            Review("Rina", "Tutor sangat membantu saat sesi tanya jawab.", 4.8f, "5 hari lalu", R.drawable.tutor),
            Review("Dedi", "Penjelasan detail dan mudah dipahami untuk pemula.", 4.7f, "2 minggu lalu", R.drawable.tutor),
            Review("Lina", "Sangat puas dengan metode pengajarannya.", 5f, "4 minggu lalu", R.drawable.tutor)
        )

        recyclerReview.layoutManager = LinearLayoutManager(this)
        recyclerReview.adapter = ReviewAdapter(reviewList)
        recyclerReview.isNestedScrollingEnabled = false
    }
}
