package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ReviewTutorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_tutor)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val etReview = findViewById<EditText>(R.id.etReview)
        val btnKirim = findViewById<Button>(R.id.btnKirimReview)
        val btnBatal = findViewById<Button>(R.id.btnTidakSekarang)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // Tombol Tidak Sekarang -> Tampilkan dialog konfirmasi
        btnBatal.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Lewati Review?")
                .setMessage("Apakah kamu yakin ingin melewati memberikan review?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    // Kembali ke tab 'Selesai' BookedTutorActivity
                    val intent = Intent(this, BookedTutorActivity::class.java)
                    intent.putExtra("tab", "selesai")
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // Tombol Kirim Review -> Tampilkan dialog konfirmasi
        btnKirim.setOnClickListener {
            val rating = ratingBar.rating
            val reviewText = etReview.text.toString().trim()

            if (rating == 0f && reviewText.isEmpty()) {
                Toast.makeText(this, "Beri rating atau tulis review terlebih dahulu.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("Kirim Review?")
                .setMessage("Kamu akan mengirimkan review dengan rating $rating bintang.\nLanjutkan?")
                .setPositiveButton("Kirim") { dialog, _ ->
                    dialog.dismiss()
                    // Simpan review ke database jika perlu
                    Toast.makeText(this, "Review berhasil dikirim!", Toast.LENGTH_LONG).show()

                    // Kembali ke tab 'Selesai' BookedTutorActivity
                    val intent = Intent(this, BookedTutorActivity::class.java)
                    intent.putExtra("tab", "selesai")
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
