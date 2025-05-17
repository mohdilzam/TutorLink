package com.example.tutorlink

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PesanActivity : AppCompatActivity() {

    data class User(val name: String, val avatarResId: Int, val isOnline: Boolean)
    data class Message(val name: String, val lastMessage: String, val avatarResId: Int, val time: String, val unreadCount: Int)

    private val activeUsers = listOf(
        User("Lisa", R.drawable.tutor, true),
        User("Yusuf", R.drawable.tutor, true),
        User("Budi", R.drawable.tutor, true),
        User("Rina", R.drawable.tutor, true),
        User("Doni", R.drawable.tutor, true),
        User("Maya", R.drawable.tutor, true)
    )

    private val messages = listOf(
        Message(
            "Kak Yusuf",
            "Halo, ayo tutor bareng aku :)",
            R.drawable.tutor,
            "12:50",
            1
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan)

        val activeUsersContainer = findViewById<LinearLayout>(R.id.activeUsersContainer)
        val messagesContainer = findViewById<LinearLayout>(R.id.messagesContainer)

        // Setup Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_chat // tandai item aktif saat ini

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_book -> {
                    startActivity(Intent(this, BookedTutorActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_chat -> true // sudah di halaman ini
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfilActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }

        // Tampilkan user aktif
        activeUsers.forEach { user ->
            val userView = layoutInflater.inflate(R.layout.item_active_user, null)
            val ivAvatar = userView.findViewById<ImageView>(R.id.ivAvatar)
            val ivOnlineDot = userView.findViewById<View>(R.id.ivOnlineDot)

            ivAvatar.setImageResource(user.avatarResId)
            ivOnlineDot.visibility = if (user.isOnline) View.VISIBLE else View.GONE

            val lp = LinearLayout.LayoutParams(150, 150)
            lp.setMargins(0, 0, 24, 0)
            userView.layoutParams = lp

            activeUsersContainer.addView(userView)
        }

        // Tampilkan pesan
        messages.forEach { message ->
            val messageView = layoutInflater.inflate(R.layout.item_message, null)

            val ivAvatar = messageView.findViewById<ImageView>(R.id.ivAvatar)
            val tvName = messageView.findViewById<TextView>(R.id.tvName)
            val tvLastMessage = messageView.findViewById<TextView>(R.id.tvLastMessage)
            val tvTime = messageView.findViewById<TextView>(R.id.tvTime)
            val tvUnreadCount = messageView.findViewById<TextView>(R.id.tvUnreadCount)

            ivAvatar.setImageResource(message.avatarResId)
            tvName.text = message.name
            tvLastMessage.text = message.lastMessage
            tvTime.text = message.time

            if (message.unreadCount > 0) {
                tvUnreadCount.visibility = View.VISIBLE
                tvUnreadCount.text = message.unreadCount.toString()
            } else {
                tvUnreadCount.visibility = View.GONE
            }

            messageView.setOnClickListener {
                val intent = Intent(this, RoomChatActivity::class.java)
                intent.putExtra("namaTutor", message.name) // pastikan key cocok
                startActivity(intent)
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 24)
            messageView.layoutParams = params

            messagesContainer.addView(messageView)
        }
    }
}
