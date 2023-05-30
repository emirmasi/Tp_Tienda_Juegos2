package  src.main.kotlin
import data.*
import repositories.*
import src.main.kotlin.src.main.kotlin.repositories.hoyConMiFormato
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GameBreak(){
    private lateinit var usuario:User
    fun crearUsuario():User?{

        var nickname: String
        var password: String
        var name:String
        var surname:String
        var money:Double

        do {
            println("Ingrese el NickName:")
            nickname = readln()
            println("Ingrese el password:")
            password = readln()
            println("Ingrese su nombre:")
            name = readln()
            println("Ingrese su apellido:")
            surname = readln()
            println("Ingrese su money:")
            money = readln().toDouble()

            if(nickname.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                println("error: algun campo esta vacio, intentelo de nuevo")
            }
        }while(nickname.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty())

            val hoy:LocalDate = LocalDate.now()

            usuario = User(UserRepository.getLastId(),nickname,password,name,surname,money,hoy.hoyConMiFormato())
            if(UserRepository.addUser(usuario)){
                println("se registro correctamente")
            }else{
                println("ocurrio un error al registrarse")
            }
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

        val precioTotal = intermediario.aplicarComision(precioDelJuego)
        if(usuario.comprobarSaldo(precioDelJuego)){
            val hoy = LocalDate.now()
            usuario.realizarCompra(PurchaseRepository.getLastId(),idGame,precioTotal,hoy.hoyConMiFormato())
            val cashBack = intermediario.calcularCashBack(hoy.hoyConMiFormato(),precioTotal)
            usuario.actualizarSaldo(precioTotal,cashBack)
        }else{
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


    fun menuOpcional():Int{///validar
        var op:Int;
      do{
          println("Que desea hacer:")
          println("1-Comprar juego")
          println("2-Mostrar Compras")
          println("3-Cargar saldo")
          println("4-Salir")
          op = readln().toInt()
          if(op in 5..0 )
              println("codigo incorrecto debe estar entre 1 y 4,intentelo de nuevo")
      }while(op in 5..0 )

        return op
    }

    fun elegirJuego():Game{
        val idJuego: Long
        println("---JUEGOS DISPONIBLES---")
        val listaDeJuegos: List<Game> = GameRepository.get()
        for(juego in listaDeJuegos){
            println(juego)
        }
        println("INGRESE EL ID DEL JUEGO QUE DESEE COMPRAR")
        idJuego = readln().toLong()
        ///tengo que validar esto
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
