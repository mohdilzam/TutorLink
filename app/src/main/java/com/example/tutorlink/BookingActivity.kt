package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class BookingActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var etLokasi: EditText
    private lateinit var btnJadwalkan: Button
    private lateinit var timeToggleGroup: MaterialButtonToggleGroup

    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Inisialisasi view
        calendarView = findViewById(R.id.calendarView)
        etLokasi = findViewById(R.id.etLokasi)
        btnJadwalkan = findViewById(R.id.btnJadwalkan)
        timeToggleGroup = findViewById(R.id.timeToggleGroup)

        // Tombol kembali
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Tanggal dipilih dari CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        }

        // Listener pilihan waktu (MaterialButtonToggleGroup)
        timeToggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                val selectedBtn = findViewById<MaterialButton>(checkedId)
                selectedTime = selectedBtn.text.toString()
            } else {
                selectedTime = ""
            }
        }

        // Klik tombol Jadwalkan
        btnJadwalkan.setOnClickListener {
            val lokasi = etLokasi.text.toString().trim()

            if (selectedDate.isEmpty() || selectedTime.isEmpty() || lokasi.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua data", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PembayaranActivity::class.java).apply {
                    putExtra("tanggal", selectedDate)
                    putExtra("jam", selectedTime)
                    putExtra("lokasi", lokasi)
                }
                startActivity(intent)
            }
        }
    }
}