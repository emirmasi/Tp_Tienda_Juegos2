package  src.main.kotlin
import data.*
import repositories.*

fun main(){
    var opcionElegida:Int
    val games:GameBreak
    var usuario: User? = null
    var cantIntentos = 0

    while( usuario == null && cantIntentos<3){///podria ser una funcion aparte
        opcionElegida = menu()
            usuario = ingresarAlSistema(opcionElegida)
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
        games = GameBreak()
        opcionElegida = games.menuOpcional()///este esta bien
        while(opcionElegida != 4){
            when(opcionElegida){
                1->{
                        games.comprarJuego(usuario!!)
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
            opcionElegida = games.menuOpcional()
        }

    }

    println("Gracias vuelvas prontos")
}

fun menu():Int{///aca tenemos que validar el numero
    var opciones:Int
    do {
        println ("ELEGIR OPCION:")
        println("1-Loguear")
        println("2-Crear Usuario")
        opciones = readln().toInt()
        if(opciones <1 && opciones >2)
            println("opcion invalida, intentelo de nuevo")
    }while(opciones <1 && opciones >2)
    return opciones
}

fun ingresarAlSistema(opcionElegida:Int): User? {
    when(opcionElegida){
        1->{
            return UserRepository.loguear()
        }
        2->{
            return UserRepository.crearUsuario()
        }
        else-> {
            println("codigo incorrecto")
            return null
        }
    }
}