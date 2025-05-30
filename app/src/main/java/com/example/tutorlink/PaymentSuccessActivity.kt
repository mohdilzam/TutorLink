package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        val btnKembali: Button = findViewById(R.id.btnKembali)

        // Ambil data dari intent
        val bookingId = intent.getStringExtra("bookingId")
        val totalAmount = intent.getIntExtra("amount", 0)
        val uid = FirebaseHelper.getCurrentUserId()
        val transactionId = FirebaseHelper.database.child("Transactions").push().key

        // ✅ Cek apakah data penting ada
        if (transactionId.isNullOrEmpty() || uid.isNullOrEmpty() || bookingId.isNullOrEmpty()) {
            Toast.makeText(this, "Data tidak lengkap", Toast.LENGTH_SHORT).show()
            return
        }

        // ✅ Simpan transaksi
        val transaction = mapOf(
            "transactionId" to transactionId,
            "userId" to uid,
            "bookingId" to bookingId,
            "amount" to totalAmount,
            "status" to "success",
            "method" to "transfer bank",
            "timestamp" to System.currentTimeMillis()
        )

        FirebaseHelper.database.child("Transactions").child(transactionId)
            .setValue(transaction)
            .addOnSuccessListener {
                Toast.makeText(this, "Transaksi berhasil disimpan", Toast.LENGTH_SHORT).show()

                // ✅ Update status booking jadi "selesai" + feedback
                FirebaseHelper.database.child("Bookings").child(bookingId).child("status")
                    .setValue("selesai")
                    .addOnSuccessListener {
                        Toast.makeText(this, "Status booking diperbarui", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal memperbarui status booking", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal menyimpan transaksi", Toast.LENGTH_SHORT).show()
            }

        btnKembali.setOnClickListener {
            val intent = Intent(this, BookedTutorActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
