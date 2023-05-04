package src.main.kotlin

class Steam( val amountDte: Double,  val createdDateDte: String): Intermediario() {

    override val amount: Double = amountDte
    override val createdDate: String  = createdDateDte

    ///aplicamos una comision de 2%
    override fun aplicarComision(): Double {
        return amount + (amount * 0.02)
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