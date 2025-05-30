package com.example.tutorlink

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import adapters.TransactionAdapter
import model.Transaction

class TransactionHistoryActivity : AppCompatActivity() {

    private lateinit var rvTransaksi: RecyclerView
    private val transactions = mutableListOf<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_history)

        rvTransaksi = findViewById(R.id.rvTransaksi)
        rvTransaksi.layoutManager = LinearLayoutManager(this)

        val uid = FirebaseHelper.getCurrentUserId()
        if (uid != null) {
            FirebaseHelper.database.child("Transactions")
                .orderByChild("userId").equalTo(uid)
                .get()
                .addOnSuccessListener { snapshot ->
                    transactions.clear()
                    for (item in snapshot.children) {
                        val tx = item.getValue(Transaction::class.java)
                        if (tx != null) transactions.add(tx)
                    }
                    rvTransaksi.adapter = TransactionAdapter(transactions)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal mengambil transaksi", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User belum login", Toast.LENGTH_SHORT).show()
        }
    }
}
