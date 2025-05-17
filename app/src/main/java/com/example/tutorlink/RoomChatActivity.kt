package com.example.tutorlink

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class RoomChatActivity : AppCompatActivity() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageView
    private lateinit var txtNamaTutor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_chat)

        // Ambil data nama tutor dengan key yang sama seperti di PesanActivity
        val namaTutor = intent.getStringExtra("tutorName") ?: "Kak Yusuf"

        txtNamaTutor = findViewById(R.id.tvNamaTutor)
        txtNamaTutor.text = namaTutor

        chatContainer = findViewById(R.id.chatContainer)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        btnSend.setOnClickListener {
            val pesan = etMessage.text.toString().trim()
            if (pesan.isNotEmpty()) {
                tambahPesanKeChat(pesan, true)
                etMessage.text.clear()
            }
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun tambahPesanKeChat(pesan: String, dariUser: Boolean) {
        val bubble = TextView(this)
        bubble.text = pesan
        bubble.setPadding(24, 16, 24, 16)
        bubble.setTextColor(if (dariUser) Color.WHITE else Color.BLACK)
        bubble.textSize = 14f
        bubble.typeface = ResourcesCompat.getFont(this, R.font.inter_medium)
        bubble.background = ContextCompat.getDrawable(
            this,
            if (dariUser) R.drawable.bg_chat_bubble_blue else R.drawable.bg_chat_bubble_gray
        )
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 12, 0, 0)
        params.gravity = if (dariUser) Gravity.END else Gravity.START
        bubble.layoutParams = params

        chatContainer.addView(bubble)
    }
}