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


        println("Ingrese el NickName:")
        nickname = readLine()!!.toString()
        println("Ingrese el password:")
        password = readLine()!!.toString()
        println("Ingrese su nombre:")
        name = readln()!!.toString()
        println("Ingrese su apellido:")
        surname = readln()!!.toString()
        println("Ingrese su money:")
        money = readln()!!.toDouble()



        usuario = data.User(UserRepository.getLastId(),nickname,password,name,surname,money,LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString())
        UserRepository.addUser(usuario)

        return usuario
    }

    fun loguear(): User?{
        var nickname: String
        var password: String
        println("Ingrese el nickName:")
        nickname = readLine()!!.toString()

        println("Ingrese el password:")
        password = readLine()!!.toString()

        return  UserRepository.login(nickname,password)

    }

    fun comprobarSaldo(usuar: User?, jueg: Game): Boolean{
        if(usuar?.money!! >= jueg.price){
            return true
        }else{
            return false
        }
    }

    fun realizarCompra(id: Long, userid: Long?, gameid: Long, precio: Double, fecha: String){
        ///damos a elegir el intermediario usamos bloques when y hacemos la compra

        compra = data.Purchase(id, userid!!,gameid,precio,fecha)
        PurchaseRepository.add(this.compra)
        ///usar bloque try catch si el al usuario no le alcanza la plata mandamos un msj, manejamos nostros la excepcion


    }
    fun actualizarSaldo(precioDelJuego: Double, usuario: User){
        usuario.money-=precioDelJuego
    }

    fun imprimirCompra(id: Long?, name: String, precioOriginal: Double, precioFinal: Double){
        // mostrar precio original, con beneficio y descuento

        println("DETALLES DE LA COMPRA:")
        println("ID Usuario: $id \n" +
                "Juego: $name  \n" +
                "Precio original: $precioOriginal \n" +
                "Precio final: $precioFinal")


    }
    fun cargarSaldo(usuario: User?, monto: Double):Double{
        usuario?.money = usuario?.money!! + monto
        return usuario?.money!!
    }
    fun mostrarHistorialDeCompra(id: Long?) :List<Purchase>{
        ///mediante el id del usuario mostramos la listade compras que realizo
        ///añadir funcion de retornarHistorial en PurcharseRepository
        ///etc mostrar con un formato lindo

        val compra: List<Purchase> = PurchaseRepository.get().filter { it.userId == id }
        if (compra.isEmpty()){
            println("No hay compras hasta el momento.")
        }
        return compra
    }
    fun menuOpcional():Int{
        var op : Int
        println("Que desea hacer:")
        println("1-Comprar juego")
        println("2-Mostrar Compras")
        println("3-Cargar saldo")
        println("4-Salir")
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
        println("Elegir un juego , poner id")
        idJuego = readln()!!.toLong()
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


