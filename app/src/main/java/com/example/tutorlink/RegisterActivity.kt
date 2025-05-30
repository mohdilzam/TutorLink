package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etHp = findViewById<EditText>(R.id.etHp)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnRegister.setOnClickListener {
            val nama = etNama.text.toString()
            val hp = etHp.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Koreksi logika: gunakan ||
            if (nama.isEmpty() || hp.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                // Disable tombol untuk mencegah klik ganda
                btnRegister.isEnabled = false

                FirebaseHelper.registerUser(
                    name = nama,
                    email = email,
                    password = password,
                    phone = hp,
                    onSuccess = {
                        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    },
                    onFailure = { errorMessage ->
                        btnRegister.isEnabled = true
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}