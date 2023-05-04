package src.main.kotlin

class EpicGames( ): Intermediario() {

    ///si se relaizo dentro de las 20:00 y 23:59 se descuenta un 1% si no un 3%
    override fun aplicarComision(amount:Double): Double {
        return 2.0
    }


}