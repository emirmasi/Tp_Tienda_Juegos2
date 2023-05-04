package src.main.kotlin

abstract class Intermediario() {

    abstract val amount: Double
    abstract val createdDate: String
    abstract fun aplicarComision(): Double
    abstract fun aplicarDescuento(): Double

}


