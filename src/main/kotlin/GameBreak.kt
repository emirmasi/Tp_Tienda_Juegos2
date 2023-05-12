package  src.main.kotlin
import data.*
import repositories.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GameBreak(){
    private lateinit var usuario: data.User
    private lateinit var compra: data.Purchase

    fun crearUsuario():User?{
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

        usuario = User(UserRepository.getLastId(),nickname,password,name,surname,money,LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString())
        UserRepository.addUser(usuario)

        return usuario
    }

    fun loguear(): User?{

        println("Ingrese el nickName:")
        val nickname: String = readln()

        println("Ingrese el password:")
        val password: String = readln()

        return  UserRepository.login(nickname,password)

    }

    fun comprobarSaldo(usuar: User?, jueg: Game): Boolean{
        return usuar?.money!! >= jueg.price
    }

    fun realizarCompra(id: Long, userid: Long?, gameid: Long, precio: Double, fecha: String){
        compra = data.Purchase(id, userid!!,gameid,precio,fecha)
        PurchaseRepository.add(this.compra)
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
    fun cargarSaldo(usuario: User?, monto: Double){
        usuario?.money = usuario?.money!! + monto
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
        println("Que desea hacer:")
        println("1-Comprar juego")
        println("2-Mostrar Compras")
        println("3-Cargar saldo")
        println("4-Salir")
        val op : Int = readln().toInt()
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
        val idJuego: Long

        println("Elegir un juego , poner id")
        idJuego = readln().toLong()

        return GameRepository.getById(idJuego)
    }
    fun elegirIntermediario():Int{
        val op: Int
        println("Elegi un intermediario")
        println("1-Steam")
        println("2-EpicGames")
        println("3-Nakama")
        op = readln().toInt()
        return op
    }
}
//class saldont(saldo: Boolean) : Exception()


