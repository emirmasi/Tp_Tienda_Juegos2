package data

import repositories.PurchaseRepository
import src.main.kotlin.Intermediario
import src.main.kotlin.saldont
import src.main.kotlin.src.main.kotlin.repositories.hoyConMiFormato
import java.time.LocalDate

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
    fun realizarCompra(intermediario:Intermediario,juego:Game){

        val precioTotal = intermediario.aplicarComision(juego.price)

        if(comprobarSaldo(precioTotal)){
            val hoy = LocalDate.now()

            val compra = Purchase(PurchaseRepository.getLastId(), this.id, juego.id,precioTotal,hoy.hoyConMiFormato())
            PurchaseRepository.add(compra)

            val cashBack = intermediario.calcularCashBack(hoy.hoyConMiFormato(),precioTotal)

            actualizarSaldo(precioTotal,cashBack)

            compra.imprimirCompra(name,juego.price,precioTotal)
        }else{
            throw saldont()
        }
    }
    fun actualizarSaldo(precioTotal: Double,cashBack: Double){
        this.money -= (precioTotal + cashBack)
    }

    fun comprobarSaldo(precioDelJuego: Double): Boolean{
        return this.money >= precioDelJuego;
    }

    fun cargarSaldo(monto: Double){

        this.money += monto
        println("nuevo saldo: ${this.money}")
    }

    fun mostrarHistorialDeCompra(){

        val historialDeCompra: List<Purchase> = PurchaseRepository.getHistorialDeCompra(this.id)

        if(historialDeCompra.isEmpty()){
            println("no posee un historial de compra")
        }else{
            for(compra in historialDeCompra){
                println(compra)
            }
        }

    }
}
