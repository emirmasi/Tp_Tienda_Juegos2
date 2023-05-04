package src.main.kotlin

class EpicGames( val amountDte: Double,  val createdDateDte: String): Intermediario() {
    override val amount: Double = amountDte
    override val createdDate: String  = createdDateDte

    ///si se relaizo dentro de las 20:00 y 23:59 se descuenta un 1% si no un 3%
    override fun aplicarComision(): Double {
        return amount + (amount * 0.0)
    }

    override fun aplicarDescuento(): Double {
        val fecha = Fecha(createdDate)
        val cantMesesRegistrado = fecha.calcularDiferenciaDeMeses()
        if(cantMesesRegistrado <= 3){
            return amount - (amount * 0.05)
        }else if(cantMesesRegistrado in 4..12){
            return amount - (amount * 0.03)
        }else {
            return amount;
        }

    }
}