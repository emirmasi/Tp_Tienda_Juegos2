package data

import repositories.PurchaseRepository

data class User(
    val id: Long,
    val nickName: String,
    val password: String,
    val name: String,
    val surname: String,
    var money: Double,
    val createdDate: String
){
    override fun toString(): String {
        return "User(id=$id, nickName='$nickName', password='$password', name='$name', surname='$surname', money=$money, createdDate='$createdDate')"
    }
    fun realizarCompra(id: Long, gameid: Long, precio: Double, fecha: String){
        var compra = Purchase(id, this.id, gameid, precio, fecha)
        PurchaseRepository.add(compra)
    }
    fun actualizarSaldo(precioTotal: Double,cashBack: Double){///funcion en usuario
        this.money -= (precioTotal + cashBack)
    }

    fun comprobarSaldo(precioDelJuego: Double): Boolean{
        return this.money >= precioDelJuego;
    }

}
