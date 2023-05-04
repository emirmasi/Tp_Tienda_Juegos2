package src.main.kotlin

class Steam(override val amount: Double, override val createdDate: String): Intermediario(amount,createdDate),interfazAplicarReglasDeNegocio {

    val fecha = Fecha(createdDate)
    override fun aplicarBeneficio(): Double {
        return 2.0
    }

    override fun aplicarDescuento(): Double {
        return 2.0
    }
}