package  src.main.kotlin
import data.*
import repositories.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GameBreak(){
    lateinit var usuario: data.User
    lateinit var compra: data.Purchase

    fun crearUsuario():User?{
        ///aca van a pedir los datos al usuario
        var nickname: String
        var password: String
        var surname:String
        var name:String
        var money:Double

        println("ingrese el nickName")
        nickname = readLine()!!.toString()
        println("ingrese el password")
        password = readLine()!!.toString()
        println("ingrese su nombre")
        name = readln()!!.toString()
        println("ingrese su apellido")
        surname = readln()!!.toString()
        println("ingrese su money")
        money = readln()!!.toDouble()

        usuario = data.User(UserRepository.getLastId(),nickname,password,name,surname,money, LocalDate.now().format(
            DateTimeFormatter.ofPattern("yyyy-mm-dd")))
        UserRepository.addUser(usuario)

        return usuario
    }

    fun loguear(): User?{
        ///bloque try catch si es null manejar la excepcion nostros
        var nickname: String
        var password: String
        println("ingrese el nickName")
        nickname = readLine()!!.toString()

        println("ingrese el password")
        password = readLine()!!.toString()

        return  UserRepository.login(nickname,password)

    }

    fun comprobarSaldo(usuar: User, jueg: Game): Boolean{
        if(usuar.money>=jueg.price){
            return true
        }else{
            return false
        }
    }

    fun realizarCompra(id: Long,userid: Long, gameid: Long,precio: Double,fecha: String){
        ///damos a elegir el intermediario usamos bloques when y hacemos la compra

        compra = data.Purchase(id,userid,gameid,precio,fecha)
        PurchaseRepository.add(this.compra)
        ///usar bloque try catch si el al usuario no le alcanza la plata mandamos un msj, manejamos nostros la excepcion


    }
    fun cargarSaldo(usuario: User,monto: Double):Double{
        usuario.money += monto
        return usuario.money
    }
    fun mostrarHistorialDeCompra(id: Long) :List<Purchase>{
        ///mediante el id del usuario mostramos la listade compras que realizo
        ///añadir funcion de retornarHistorial en PurcharseRepository
        ///etc mostrar con un formato lindo
        val compra: List<Purchase> = PurchaseRepository.get().filter { it.userId == id }
        return compra
    }
    fun menuOpcional():Int{
        var op : Int
        println("que desea hacer")
        println("1-comprar juego")
        println("2-mostrarCompra")
        println("3-cargar saldo")
        println("4-salir")
        op = readln()!!.toInt()
        return op
    }
    fun mostrarJuego(){
        println("lista de juegos")
        val listaDeJuegos: List<Game> = GameRepository.get()
        for(juego in listaDeJuegos){
            println(juego)
        }
    }
    fun elegirJuego():Game?{
        var idJuego: Long
        println("elegi un juego , poner id")
        idJuego = readln()!!.toLong()
        println("id del juego: $idJuego")
        return GameRepository.getById(idJuego)
    }
    fun elegirIntermediario():Int{
        var op: Int
        println("Elegi un intermediario")
        println("1-Steam")
        println("2-EpicGames")
        println("3-Nakama")
        op = readln()!!.toInt()
        return op
    }
}
//class saldont(saldo: Boolean) : Exception()


