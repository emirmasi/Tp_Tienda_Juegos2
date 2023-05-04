package src.main.kotlin

class Nakama() : Intermediario(){

    ///si la compra se realiza un sabado o un domigo se aplica una comision de 3% si no 0.075%
    override fun aplicarComision(amount: Double): Double {
        return 2.0
    }
}