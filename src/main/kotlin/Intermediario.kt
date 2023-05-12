package src.main.kotlin

abstract class Intermediario() {
    abstract fun aplicarComision(precio: Double):Double

    fun aplicarDescuento(createdDate: String?, amount:Double):Double {

        val descuento: Double
        val fecha  = Fecha(createdDate)
        val mesCreacion = fecha.calcularDiferenciaDeMeses()

        descuento = when(mesCreacion){
            in 1..3 -> {
                0.05
            }

            in 4..12 ->{
                0.03
            }

            else -> 0.0
        }
        return amount - amount.times(descuento)
    }


}


