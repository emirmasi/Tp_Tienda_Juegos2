package data

data class Purchase(
    val id: Long,
    val userId: Long,
    val gameId: Long,
    val amount: Double,
    val createdDate: String,
)
