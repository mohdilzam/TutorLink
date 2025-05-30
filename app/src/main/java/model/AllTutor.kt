package model

data class AllTutor(
    val nama: String = "",
    val pelajaran: String = "",
    val lokasi: String = "",
    val biaya: String = "",
    val jarak: Int = 0,
    val deskripsi: String = "",
    val rating: String = "",
    val imageUrl: String = "" // ← pakai URL gambar dari Firebase
)
