package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PaymentSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        val btnKembali: Button = findViewById(R.id.btnKembali)

        btnKembali.setOnClickListener {
            val intent = Intent(this, BookedTutorActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}