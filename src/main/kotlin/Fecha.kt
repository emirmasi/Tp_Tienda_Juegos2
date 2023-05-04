package src.main.kotlin
///formato de fecha "yyyy-mm-dd"
import java.time.*
class Fecha(val fecha: String) {

    val fechaDeRegistro: LocalDate = LocalDate.parse(fecha)


    fun calcularDiferenciaDeMeses():Int{
        val hoy: LocalDate = LocalDate.now()
        var period : Period = Period.between(fechaDeRegistro, hoy)
        return period.months;
    }

}