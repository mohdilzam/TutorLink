package com.example.tutorlink

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import model.Booking
import model.KategoriPelajaran
import model.Pembayaran
import model.Tutor
import model.User

object FirebaseHelper {

    val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    // Register user
    fun registerUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid
                    if (uid == null) {
                        onFailure("UID tidak ditemukan")
                        return@addOnCompleteListener
                    }

                    val user = User(uid, name, email, phone)
                    database.child("Users").child(uid).setValue(user)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener {
                            Log.e("FirebaseHelper", "Gagal menyimpan user: ${it.message}")
                            onFailure(it.message ?: "Gagal menyimpan data")
                        }
                } else {
                    onFailure(task.exception?.message ?: "Registrasi gagal")
                }
            }
    }

    // Login user
    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(task.exception?.message ?: "Login gagal")
                }
            }
    }

    // Ambil data user berdasarkan UID
    fun getUserData(
        uid: String,
        onSuccess: (User) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("Users").child(uid).get()
            .addOnSuccessListener { snapshot ->
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    onSuccess(user)
                } else {
                    onFailure("User tidak ditemukan")
                }
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Gagal mengambil data")
            }
    }

    // Update data user
    fun updateUserProfile(
        uid: String,
        updatedUser: User,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("Users").child(uid).setValue(updatedUser)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Gagal update") }
    }

    // Booking tutor
    fun bookTutor(
        userId: String,
        tutorId: String,
        tanggal: String,
        jam: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val bookingId = database.child("Bookings").push().key
        if (bookingId == null) {
            onFailure("Gagal membuat ID booking")
            return
        }

        val booking = Booking(
            bookingId = bookingId,
            userId = userId,
            tutorId = tutorId,
            tanggal = tanggal,
            jam = jam,
            status = "pending"
        )

        database.child("Bookings").child(bookingId).setValue(booking)
            .addOnSuccessListener { onSuccess(bookingId) }
            .addOnFailureListener { onFailure(it.message ?: "Gagal booking tutor") }
    }

    // Ambil semua data tutor (node "tutor")
    fun getAllTutors(
        onSuccess: (List<Tutor>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("tutor").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tutors = mutableListOf<Tutor>()
                for (child in snapshot.children) {
                    val tutor = child.getValue(Tutor::class.java)
                    if (tutor != null) {
                        tutors.add(tutor)
                    } else {
                        Log.w("FirebaseHelper", "Data tutor null atau tidak sesuai: ${child.key}")
                    }
                }

                if (tutors.isNotEmpty()) {
                    Log.d("FirebaseHelper", "Jumlah tutor yang diambil: ${tutors.size}")
                    onSuccess(tutors)
                } else {
                    onFailure("Tidak ada data tutor ditemukan")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseHelper", "Gagal mengambil tutor: ${error.message}")
                onFailure("Gagal mengambil data tutor: ${error.message}")
            }
        })
    }

    // Ambil semua kategori pelajaran (node "kategori_pelajaran")
    fun getAllKategoriPelajaran(
        onSuccess: (List<KategoriPelajaran>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("kategori_pelajaran")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val kategoriList = mutableListOf<KategoriPelajaran>()
                    for (data in snapshot.children) {
                        val nama = data.child("nama").getValue(String::class.java) ?: continue
                        val deskripsi = data.child("deskripsi").getValue(String::class.java) ?: ""
                        val imageUrl = data.child("imageUrl").getValue(String::class.java) ?: ""
                        kategoriList.add(KategoriPelajaran(nama, deskripsi, imageUrl))
                    }
                    if (kategoriList.isNotEmpty()) {
                        Log.d("FirebaseHelper", "Jumlah kategori yang diambil: ${kategoriList.size}")
                        onSuccess(kategoriList)
                    } else {
                        onFailure("Tidak ada data kategori ditemukan")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseHelper", "Gagal mengambil kategori: ${error.message}")
                    onFailure(error.message)
                }
            })
    }

    fun simpanPembayaran(
        pembayaran: Pembayaran,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val pembayaranId = database.child("Pembayaran").push().key
        if (pembayaranId == null) {
            onFailure("Gagal membuat ID pembayaran")
            return
        }

        val data = pembayaran.copy(pembayaranId = pembayaranId)
        database.child("Pembayaran").child(pembayaranId).setValue(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Gagal menyimpan pembayaran") }
    }

    // Ambil UID user yang sedang login
    fun getCurrentUserId(): String? = auth.currentUser?.uid
}