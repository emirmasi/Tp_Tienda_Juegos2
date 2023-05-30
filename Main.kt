package  src.main.kotlin
import data.*
import repositories.*
import java.lang.Exception

fun main(){
    var opcionElegida:Int
    val games = GameBreak()
    var usuario: User? = null
    var cantIntentos = 0

    while( usuario == null && cantIntentos<3){///podria ser una funcion aparte
        opcionElegida = menu()
            usuario = ingresarAlSistema(opcionElegida,games)
            if(usuario==null){
                println("intentelo de nuevo")
                cantIntentos++;
            }else{
                println("exito al entrar al sistema")
            }
    }
    if(cantIntentos == 3){
        println("cantidad de intentos maximo alcanzados , vuelva intentarlo mas tarde")
    }else{
        opcionElegida = games.menuOpcional()///este esta bien
        while(opcionElegida != 4){
            when(opcionElegida){
                1->{
                    val intermediarioElegido = games.elegirIntermediario()
                    val juego: Game = games.elegirJuego()
                    intentarComprarJuego(intermediarioElegido,juego.id,juego.price,usuario!!,games);
                }
                2->{
                    PurchaseRepository.mostrarHistorialDeCompra(usuario?.id)
                }
                3->{
                    println("ingresa monto deseado para cargar")
                    val monto:Double = readln().toDouble()
                    usuario!!.cargarSaldo(monto)
                    }
                }
        }
        opcionElegida = games.menuOpcional()
    }

    println("Gracias vuelvas prontos")
}

fun menu():Int{///aca tenemos que validar el numero
    println ("ELEGIR OPCION:")
    println("1-Loguear")
    println("2-Crear Usuario")
    val opciones: Int = readln().toInt()
    return opciones
}
fun intentarComprarJuego(
    intermediarioElegido: Int,
    idJuego: Long,
    precioDelJuego:Double,
    usuario: User,
    games: GameBreak
){
    when(intermediarioElegido){
        1->{
            try {
                val steam = Steam()
                games.comprarJuego(steam,idJuego,precioDelJuego,usuario)
                println("compra exitosa")
            }catch (e: saldont){
                println(e.getMensaje())
            }

        }
        2->{
            try {
                val epicGames = EpicGames()
                games.comprarJuego(epicGames,idJuego,precioDelJuego,usuario)
                println("compra exitosa")
            }catch (e: saldont){
                println(e.getMensaje())
            }

        }
        3->{
            val nakama = Nakama()
            try {
                games.comprarJuego(nakama,idJuego,precioDelJuego,usuario)
                println("compra exitosa")
            }catch(e: saldont){
                println(e.getMensaje())
            }

        }

    }
}
fun ingresarAlSistema(opcionElegida:Int,sistema:GameBreak): User? {
    when(opcionElegida){///puede ser una funcion este when
        1->{
            return sistema.loguear()
        }
        2->{
            return sistema.crearUsuario()
        }
        else-> {
            println("codigo incorrecto")
            return null
        }
    }
}