package src.main.kotlin

abstract class Intermediario() {
    abstract fun aplicarComision(precio: Double):Double

    fun aplicarDescuento(createdDate: String, amount:Double):Double {

        var descuento: Double
        val fecha  = Fecha(createdDate)
        val mesCreacion = fecha.calcularDiferenciaDeMeses()

        when(mesCreacion){
            in 1..3 -> {
                descuento = 0.05
            }
            in 4..12 ->{
                descuento =  0.03
            }
            else -> descuento=0.0
        }
        return amount - amount.times(descuento)
    }


}


