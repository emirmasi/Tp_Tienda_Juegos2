package src.main.kotlin

interface interfazAplicarReglasDeNegocio {
    ///implementamos esta interfaz para ahcer herencia multiple de intermediario
    open fun aplicarDescuento():Double
    open fun aplicarBeneficio():Double
}