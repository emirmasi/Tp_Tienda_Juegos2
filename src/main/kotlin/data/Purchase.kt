package data

import repositories.GameRepository
import repositories.PurchaseRepository

data class Purchase(
    val id: Long,
    val userId: Long,
    val gameId: Long,
    val amount: Double,
    val createdDate: String,
){
    fun imprimirCompra(name: String, precioOriginal: Double, precioFinal: Double){
        println("DETALLES DE LA COMPRA:")
        println("Usuario: $name \n" +
                "Juego: ${GameRepository.getById(gameId).name}  \n" +
                "Precio original: $precioOriginal \n" +
                "Precio final: $precioFinal")

    }

    override fun toString(): String {
        return "Purchase(id=$id, userId=$userId, gameId=$gameId, amount=$amount, createdDate='$createdDate')"
    }


}
