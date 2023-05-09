package  src.main.kotlin
import data.*
import repositories.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main(){
    var opciones:Int
    val games = GameBreak()

    lateinit var usuario: data.User
    ///obviammente validamos todo
    //funcion menuInicio

    println("1-loguear")
    println("2-crearUsuario")
    opciones = readln()!!.toInt()
    if(opciones==1){
        usuario = games.loguear()!!
        println("logue exitoso")
    }


    if(opciones == 2){
        usuario = games.crearUsuario()!!
        println("se creo el usuario correctamente")
    }


    opciones = games.menuOpcional()

    while(opciones != 4){
        when(opciones){
            1->{
                var inter = games.elegirIntermediario()
                games.mostrarJuego()
                lateinit var juego: data.Game
                juego = games.elegirJuego()!!

                if(juego == null)
                    println("el juego no se encontro , id incorrecto")


                when(inter){
                    1->{ val steam = Steam()
                        try {
                            if(games.comprobarSaldo(usuario,juego)){
                                var precio:Double = juego.price
                                precio  = steam.aplicarComision(precio)
                                precio = steam.aplicarDescuento(usuario.createdDate,precio)
                                val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
                                println(hoy)
                                games.realizarCompra(PurchaseRepository.getLastId(),usuario.id,juego.id,precio,hoy)
                            }else{
                                throw RuntimeException("saldo insuficiente")
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
                                precio = epicGames.aplicarDescuento(usuario.createdDate,precio)
                                val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"))
                                games.realizarCompra(PurchaseRepository.getLastId(),usuario.id,juego.id,precio,hoy)
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
                                precio = nakama.aplicarDescuento(usuario.createdDate,precio)
                                val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"))
                                games.realizarCompra(PurchaseRepository.getLastId(),usuario.id,juego.id,precio,hoy)
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
                val historialDecompra = games.mostrarHistorialDeCompra(usuario.id)
                for(compra in historialDecompra){
                    println(compra)
                }
            }
            3->{
                var monto:Double
                println("ingresa monto deseado para cargar")
                monto = readln()!!.toDouble()
                var nuevoSaldo = games.cargarSaldo(usuario,monto)
                println("nuevo saldo: $nuevoSaldo")
            }
        }
        opciones = games.menuOpcional()
    }

    println("gracias vuelvas prontos")
}


