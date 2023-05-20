package  src.main.kotlin
import data.*
import repositories.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GameBreak(){
    private lateinit var usuario:User

    fun crearUsuario():User?{///este es de user
        ///aca van a pedir los datos al usuario

        println("Ingrese el NickName:")
        val nickname: String = readln()
        println("Ingrese el password:")
        val password: String = readln()
        println("Ingrese su nombre:")
        val name:String = readln()
        println("Ingrese su apellido:")
        val surname:String = readln()
        println("Ingrese su money:")
        val money:Double = readln().toDouble()

        usuario = User(UserRepository.getLastId(),nickname,password,name,surname,money,
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString())
        UserRepository.addUser(usuario)

        return usuario
    }
    fun loguear(): User?{///este es de user

        println("Ingrese el nickName:")
        val nickname: String = readln()

        println("Ingrese el password:")
        val password: String = readln()

        return  UserRepository.login(nickname,password)

    }
    fun comprarJuego(intermediario: Intermediario,idGame: Long,precioDelJuego: Double,usuario: User){
        ///logica de compra
        val precioTotal = intermediario.aplicarComision(precioDelJuego)
        if(usuario.comprobarSaldo(precioDelJuego)){//si puede comprar entonces realizo la compra
            val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
            usuario.realizarCompra(PurchaseRepository.getLastId(),idGame,precioTotal,hoy)
            val cashBack = intermediario.calcularCashBack(hoy,precioTotal)
            usuario.actualizarSaldo(precioTotal,cashBack);
        }else{
            ///enviar una excepcion
            throw saldont()
        }

    }
    ///esta funcion puede ser el de resumen de compra de purchase
    fun imprimirCompra(id: Long?, name: String, precioOriginal: Double, precioFinal: Double){
        println("DETALLES DE LA COMPRA:")
        println("ID Usuario: $id \n" +
                "Juego: $name  \n" +
                "Precio original: $precioOriginal \n" +
                "Precio final: $precioFinal")

    }
    fun cargarSaldo(usuario: User?){
        println("ingresa monto deseado para cargar")
        val monto:Double = readln().toDouble()
        usuario?.money = usuario?.money!! + monto
        println("nuevo saldo: ${usuario.money}")
    }
    fun mostrarHistorialDeCompra(id: Long?){

        val historialDeCompra: List<Purchase> = PurchaseRepository.get().filter { it.userId == id }
        for(compra in historialDeCompra){
            println(compra)
        }

    }
    fun menuOpcional():Int{///validar

        println("Que desea hacer:")
        println("1-Comprar juego")
        println("2-Mostrar Compras")
        println("3-Cargar saldo")
        println("4-Salir")
        val op : Int = readln().toInt()
        return op
    }

    fun elegirJuego():Game{
        val idJuego: Long
        println("---JUEGOS DISPONIBLES---")
        val listaDeJuegos: List<Game> = GameRepository.get()
        for(juego in listaDeJuegos){
            println(juego)
        }
        println("Elegir un juego , poner id")
        idJuego = readln().toLong()

        return GameRepository.getById(idJuego)
    }
    fun elegirIntermediario():Int{///validarlo

        val op: Int
        println("Elegi un intermediario")
        println("1-Steam")
        println("2-EpicGames")
        println("3-Nakama")
        op = readln().toInt()

        return op
    }
}
class saldont() : Exception(){
    fun getMensaje():String = "saldo insuficiente"
}
