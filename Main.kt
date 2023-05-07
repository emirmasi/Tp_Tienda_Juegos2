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
    do{
        if(games.realizarCompra()){
            println("COMPRA EXITOSA")
        }else{
            println("SU COMPRA NO SE PUDO REALIZAR")
        }
        println("quiere seguir comprando S(si) N(no) ")
        var op = readln()!!.toString()


    }while(op.uppercase() == "S")


    ///
    games.mostrarHistorialDeCompra()
    ///

    println("gracias vuelvas prontos")


}