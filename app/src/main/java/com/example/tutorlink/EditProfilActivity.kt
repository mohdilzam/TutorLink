package com.example.tutorlink

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditProfilActivity : AppCompatActivity() {

    private lateinit var ivProfilePhoto: ImageView
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        ivProfilePhoto = findViewById(R.id.ivProfilePhoto)
        val btnEditPhoto = findViewById<ImageView>(R.id.btnEditPhoto)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnSave = findViewById<ImageView>(R.id.btnSave)
        val btnBatal = findViewById<Button>(R.id.btnBatal)

        btnEditPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Pilih Foto"), PICK_IMAGE_REQUEST)
        }

        btnBack.setOnClickListener {
            showConfirmationDialog("Apakah Anda ingin membatalkan perubahan?", "Kembali")
        }

        btnBatal.setOnClickListener {
            showConfirmationDialog("Apakah Anda yakin ingin membatalkan perubahan?", "Batal")
        }

        btnSave.setOnClickListener {
            showConfirmationDialog("Simpan perubahan profil?", "Simpan")
        }
    }

    private fun showConfirmationDialog(message: String, action: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("Iya") { _, _ ->
                when (action) {
                    "Batal", "Kembali" -> {
                        finish() // kembali ke halaman sebelumnya
                    }
                    "Simpan" -> {
                        Toast.makeText(this, "Profil disimpan", Toast.LENGTH_SHORT).show()
                        // Tambahkan logika simpan ke database / backend di sini
                        finish()
                    }
                }
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            ivProfilePhoto.setImageURI(imageUri)
        }
    }
}
