package src.main.kotlin

class Steam(): Intermediario() {

    ///aplicamos una comision de 2%
    override fun aplicarComision(amount : Double): Double {
        return amount + (amount * 0.02)
    }

}