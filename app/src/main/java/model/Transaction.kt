package model

data class Transaction(
    val transactionId: String = "",
    val userId: String = "",
    val bookingId: String = "",
    val amount: Int = 0,
    val status: String = "",
    val method: String = "",
    val timestamp: Long = 0L
)
