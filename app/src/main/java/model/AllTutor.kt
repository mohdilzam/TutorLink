package model

data class AllTutor(
    val nama: String,
    val pelajaran: String,
    val lokasi: String,
    val biaya: String,      // harus String
    val jarak: Int,
    val deskripsi: String,  // harus String
    val rating: String,     // harus String
    val imageResId: Int
)
