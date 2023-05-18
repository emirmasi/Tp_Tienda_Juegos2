package src.main.kotlin
///formato de fecha "yyyy/mm/dd"
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


class Fecha(val fecha: String?) {///nos trae una fecha con formato yyyy/mm/dd y deberia ser yyyy-mm-dd para le localDate
    var formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val fechaDeRegistro: LocalDate = LocalDate.parse(fecha,formatter)

///pasarlo como una funcion de extension
    fun calcularDiferenciaDeMeses():Int{
        val hoy: LocalDate = LocalDate.now()
        var period : Period = Period.between(fechaDeRegistro, hoy)
        return period.months;
    }

}