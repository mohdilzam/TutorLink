package model

data class Tutor(
    val nama: String = "",
    val pelajaran: String = "",
    val lokasi: String = "",
    val biaya: String = "",
    val jarak: Int = 0,
    val deskripsi: String = "",
    val rating: Float = 0f,
    val imageUrl: String = "" // ⬅ tambahkan properti ini
)


