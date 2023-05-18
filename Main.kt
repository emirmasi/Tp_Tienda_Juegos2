package  src.main.kotlin
import data.*
import repositories.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main(){
    var opcionElegida:Int
    val games = GameBreak()
    var usuario: User? = null
    var cantIntentos = 0

    while( usuario == null && cantIntentos<3){
        opcionElegida = menu()
        when(opcionElegida){
            1->{
                try {
                    usuario = games.loguear()
                    if(usuario == null){
                        cantIntentos++
                        throw RuntimeException("logueo incorrecto,intentelo de nuevo")
                    }
                    println("logue exitoso")


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
    }else{///hacer una funcion de altoOrden que le mandemos el intermediario y algo mas
        opcionElegida = games.menuOpcional()///este esta bien

        while(opcionElegida != 4){
            when(opcionElegida){
                1->{
                    val intermediarioElegido = games.elegirIntermediario()
                    val juego: Game = games.elegirJuego()!!///en elegir jugo me debeira mostrar el juego ya traerlo
                    when(intermediarioElegido){///funcion comprarJuego que nos traiga el intermediario
                        1->{
                            try {
                                val steam = Steam()
                                games.comprarJuego(steam,juego.id,juego.price,usuario!!)
                                println("compra exitosa")
                            }catch (e: saldont){
                                println(e.getMensaje())
                            }

                        }
                        2->{
                            try {
                                val epicGames = EpicGames()
                                games.comprarJuego(epicGames,juego.id,juego.price,usuario!!)
                                println("compra exitosa")
                            }catch (e: saldont){
                                println(e.getMensaje())
                            }

                        }
                        3->{
                            val nakama = Nakama()
                            try {
                                games.comprarJuego(nakama,juego.id,juego.price,usuario!!)
                                println("compra exitosa")
                            }catch(e: saldont){
                                println(e.getMensaje())
                            }

                        }

                    }

                }
                2->{///funcion aparte gameBreak
                    val historialDecompra = games.mostrarHistorialDeCompra(usuario?.id)
                    for(compra in historialDecompra){///este for no va , la funcion mostrarHistorialDecompra ya lo deberia mostrar
                        println(compra)
                    }
                }
                3->{///funcion aparte en GameBreak
                    println("ingresa monto deseado para cargar")
                    val monto:Double = readln().toDouble()
                    games.cargarSaldo(usuario,monto)
                    println("nuevo saldo: ${usuario?.money}")
                }
            }
            opcionElegida = games.menuOpcional()
        }


    }
    println("Gracias vuelva pronto")

}

fun menu():Int{///aca tenemos que validar el numero
    println ("ELEGIR OPCION:")
    println("1-Loguear")
    println("2-Crear Usuario")
    val opciones: Int = readln().toInt()
    return opciones
}
