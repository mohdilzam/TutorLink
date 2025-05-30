package com.example.tutorlink

import adapters.TutorAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.Tutor

class AllTutorsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var tutorAdapter: TutorAdapter
    private var tutorList: List<Tutor> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tutor)

        recyclerView = findViewById(R.id.recyclerAllTutor)
        searchBar = findViewById(R.id.edtSearchTutor)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener { finish() }

        FirebaseHelper.getAllTutors(
            onSuccess = { tutor ->
                tutorList = tutor
                tutorAdapter = TutorAdapter(tutorList)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = tutorAdapter

                // ✅ Tambahkan pencarian
                searchBar.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        tutorAdapter.filter(s.toString())
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                })
            },
            onFailure = { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        )
    }
}
