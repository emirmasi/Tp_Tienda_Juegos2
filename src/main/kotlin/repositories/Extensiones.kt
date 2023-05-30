package src.main.kotlin.src.main.kotlin.repositories

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun LocalDate.hoyConMiFormato():String{
    return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
}
fun LocalDate.calcularDifenMeses(fecha: String):Int{
    var formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val fechaDeRegistro: LocalDate = LocalDate.parse(fecha,formatter)

    val hoy: LocalDate = LocalDate.now()
    var period : Period = Period.between(fechaDeRegistro, hoy)
    return period.months;
}