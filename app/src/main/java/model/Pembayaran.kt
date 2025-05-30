package model

data class Pembayaran(
    val pembayaranId: String = "",
    val userId: String = "",
    val bookingId: String = "",
    val metode: String = "",
    val bank: String = "",
    val noRekening: String = "",
    val rekPenerima: String = "",
    val namaPenerima: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
