package model

data class Review(
    val namaUser: String,
    val isiReview: String,
    val rating: Float,
    val waktuReview: String,
    val fotoResId: Int // drawable resource id
)
