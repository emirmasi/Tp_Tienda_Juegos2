import src.main.kotlin.GameBreak

fun main(){
    var opciones:Int
     val games = GameBreak()

    ///obviammente validamos todo
    //funcion menuInicio
    println("1-loguear")
    println("2-crearUsuario")
    opciones = readln()!!.toInt()
    if(opciones==1)
        games.loguear()

    if(opciones == 2)
        games.crearUsuario()

    ///
    games.realizarCompra()

    ///
    games.mostrarHistorialDeCompra()
    ///

    println("gracias vuelvas prontos")


}