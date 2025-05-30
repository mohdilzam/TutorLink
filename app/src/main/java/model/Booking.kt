package model

data class Booking(
    var bookingId: String? = null,
    val userId: String? = null,
    val tutorId: String? = null,
    val tanggal: String? = null,
    val jam: String? = null,
    val status: String? = null,
    val tutorName: String? = null,
    val subject: String? = null,
    val date: String? = null,
    val time: String? = null,
    val duration: String? = null,
    val location: String? = null
)
