package com.example.tutorlink

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PembayaranActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var btnBayar: Button
    private lateinit var btnBankTransfer: Button
    private lateinit var btnCash: Button
    private lateinit var etNoRekening: EditText
    private lateinit var etRekPenerima: EditText
    private lateinit var etNamaPenerima: EditText

    // Bank buttons
    private lateinit var tvPilihBank: TextView
    private lateinit var tvLabelRekPengirim: TextView
    private lateinit var tvLabelRekPenerima: TextView
    private lateinit var tvLabelNamaPenerima: TextView
    private lateinit var btnMandiri: ImageButton
    private lateinit var btnBSI: ImageButton
    private lateinit var btnBCA: ImageButton

    private var selectedPaymentMethod = "Bank Transfer"
    private var selectedBank: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        // Inisialisasi views
        btnBack = findViewById(R.id.btnBack)
        btnBayar = findViewById(R.id.btnBayar)
        btnBankTransfer = findViewById(R.id.btnBankTransfer)
        btnCash = findViewById(R.id.btnCash)
        etNoRekening = findViewById(R.id.etNoRekening)
        etRekPenerima = findViewById(R.id.etRekPenerima)
        etNamaPenerima = findViewById(R.id.etNamaPenerima)

        tvPilihBank = findViewById(R.id.tvPilihBank)
        btnMandiri = findViewById(R.id.btnMandiri)
        btnBSI = findViewById(R.id.btnBSI)
        btnBCA = findViewById(R.id.btnBCA)

        tvLabelRekPengirim = findViewById(R.id.tvLabelRekPengirim)
        tvLabelRekPenerima = findViewById(R.id.tvLabelRekPenerima)
        tvLabelNamaPenerima = findViewById(R.id.tvLabelNamaPenerima)

        // Back button
        btnBack.setOnClickListener {
            finish()
        }

        // Default pilihan metode pembayaran Bank Transfer aktif
        setPaymentMethod("Bank Transfer")

        btnBankTransfer.setOnClickListener {
            setPaymentMethod("Bank Transfer")
        }

        btnCash.setOnClickListener {
            setPaymentMethod("Cash")
        }

        // Handle pemilihan bank
        val bankButtons = listOf(btnMandiri, btnBSI, btnBCA)
        bankButtons.forEach { btn ->
            btn.setOnClickListener {
                selectBank(btn)
            }
        }

        btnBayar.setOnClickListener {
            // Contoh: validasi sederhana
            if (selectedPaymentMethod == "Bank Transfer") {
                if (etNoRekening.text.isEmpty() || etRekPenerima.text.isEmpty() || etNamaPenerima.text.isEmpty()) {
                    Toast.makeText(this, "Lengkapi semua data rekening", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (selectedBank == null) {
                    Toast.makeText(this, "Pilih bank terlebih dahulu", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val intent = Intent(this, PaymentSuccessActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setPaymentMethod(method: String) {
        selectedPaymentMethod = method
        if (method == "Bank Transfer") {
            btnBankTransfer.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
            btnBankTransfer.setTextColor(Color.WHITE)

            btnCash.setBackgroundColor(ContextCompat.getColor(this, R.color.light_gray))
            btnCash.setTextColor(Color.BLACK)

            // Tampilkan input bank dan rekening
            showBankSelection(true)
        } else {
            btnCash.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
            btnCash.setTextColor(Color.WHITE)

            btnBankTransfer.setBackgroundColor(ContextCompat.getColor(this, R.color.light_gray))
            btnBankTransfer.setTextColor(Color.BLACK)

            // Sembunyikan input bank dan rekening
            showBankSelection(false)
        }
    }

    private fun showBankSelection(show: Boolean) {
        val visibility = if (show) android.view.View.VISIBLE else android.view.View.GONE
        tvPilihBank.visibility = visibility
        tvLabelRekPengirim.visibility = visibility
        tvLabelRekPenerima.visibility = visibility
        tvLabelNamaPenerima.visibility = visibility
        btnMandiri.visibility = visibility
        btnBSI.visibility = visibility
        btnBCA.visibility = visibility
        etNoRekening.visibility = visibility
        etRekPenerima.visibility = visibility
        etNamaPenerima.visibility = visibility
    }

    private fun selectBank(selectedBtn: ImageButton) {
        // Reset semua border bank button
        val bankButtons = listOf(btnMandiri, btnBSI, btnBCA)
        bankButtons.forEach { btn ->
            btn.background = null
        }

        // Tambahkan border highlight pada yang dipilih
        val border = GradientDrawable()
        border.setColor(Color.TRANSPARENT) // Background transparan
        border.setStroke(4, ContextCompat.getColor(this, R.color.teal_700)) // Border warna teal_700
        border.cornerRadius = 12f

        selectedBtn.background = border
        selectedBank = selectedBtn
    }
}