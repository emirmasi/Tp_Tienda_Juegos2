package src.main.kotlin

class Steam(): Intermediario() {

    override fun aplicarComision(precio: Double): Double = precio*1.02

}