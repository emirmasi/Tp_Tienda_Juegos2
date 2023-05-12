package  src.main.kotlin
import data.*
import repositories.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main(){
    var opciones:Int
    val games = GameBreak()
    var usuario: User? = null
    var entroCorectamente : Boolean = false
    var cantIntentos = 0

    while(!entroCorectamente && cantIntentos<3){
        opciones = menu()
        when(opciones){
            1->{
                try {
                    usuario = games.loguear()
                    if(usuario == null){
                        cantIntentos++
                        throw RuntimeException("logueo incorrecto,intentelo de nuevo")
                    }
                    println("logue exitoso")
                    entroCorectamente = true

                }catch(e: Exception){
                    println(e.message)
                }
            }
            2->{
                try {
                    usuario = games.crearUsuario()
                    if(usuario == null){
                        cantIntentos++
                        throw RuntimeException("no se pudo crear usuario, intentelo de nuevo")
                    }
                    println("se creo el usuario exitosamente")
                    entroCorectamente = true
                }catch (e: Exception){
                    println(e.message)
                }
            }
            else-> {
            println("codigo incorrecto")
            }

        }

    }

    if(cantIntentos == 3){
        println("cantidad de intentos maximo alcanzados , vuelva intentarlo mas tarde")
    }else{
        opciones = games.menuOpcional()

        while(opciones != 4){
            when(opciones){
                1->{
                    val inter = games.elegirIntermediario()
                    games.mostrarJuego()
                    val juego: data.Game = games.elegirJuego()!!

                    when(inter){
                        1->{ val steam = Steam()
                            try {
                                if(games.comprobarSaldo(usuario,juego)){
                                    var precio:Double = juego.price
                                    precio  = steam.aplicarComision(precio)
                                    precio = steam.aplicarDescuento(usuario?.createdDate,precio)
                                    val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()

                                    games.realizarCompra(PurchaseRepository.getLastId(),usuario?.id,juego.id,precio,hoy)
                                    games.actualizarSaldo(precio,usuario!!)
                                    games.imprimirCompra(usuario.id,juego.name,juego.price,precio)
                                }else{
                                    throw RuntimeException("Saldo Insuficiente")
                                }
                            }catch(e: RuntimeException){
                                println(e.message)
                            }
                        }
                        2->{val epicGames = EpicGames()
                            try {
                                if(games.comprobarSaldo(usuario,juego)){
                                    var precio:Double = juego.price
                                    precio  = epicGames.aplicarComision(precio)
                                    precio = epicGames.aplicarDescuento(usuario?.createdDate,precio)
                                    val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
                                    games.realizarCompra(PurchaseRepository.getLastId(),usuario?.id,juego.id,precio,hoy)
                                    games.actualizarSaldo(precio,usuario!!)
                                    games.imprimirCompra(usuario.id,juego.name,juego.price,precio)
                                }else{
                                    throw RuntimeException("saldo insuficiente")
                                }
                            }catch(e: RuntimeException){
                                println(e.message)
                            }

                        }
                        3->{val nakama = Nakama()
                            try {
                                if(games.comprobarSaldo(usuario,juego)){
                                    var precio:Double = juego.price
                                    precio  = nakama.aplicarComision(precio)
                                    precio = nakama.aplicarDescuento(usuario?.createdDate,precio)
                                    val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
                                    games.realizarCompra(PurchaseRepository.getLastId(),usuario?.id,juego.id,precio,hoy)
                                    games.actualizarSaldo(precio,usuario!!)
                                    games.imprimirCompra(usuario.id,juego.name,juego.price,precio)
                                }else{
                                    throw RuntimeException("saldo insuficiente")
                                }
                            }catch(e: RuntimeException){
                                println(e.message)
                            }
                        }
                    }
                }
                2->{
                    val historialDecompra = games.mostrarHistorialDeCompra(usuario?.id)
                    for(compra in historialDecompra){
                        println(compra)
                    }
                }
                3->{
                    println("ingresa monto deseado para cargar")
                    val monto:Double = readln().toDouble()
                    games.cargarSaldo(usuario,monto)
                    println("nuevo saldo: ${usuario?.money}")
                }
            }
            opciones = games.menuOpcional()
        }


    }
    println("Gracias vuelva pronto")

}

fun menu():Int{
    println ("ELEGIR OPCION:")
    println("1-Loguear")
    println("2-Crear Usuario")
    val opciones: Int = readln().toInt()
    return opciones
}
