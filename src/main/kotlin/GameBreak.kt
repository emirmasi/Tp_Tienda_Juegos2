package  src.main.kotlin
import data.*
import repositories.*
import src.main.kotlin.src.main.kotlin.repositories.hoyConMiFormato
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GameBreak(){

    fun comprarJuego(usuario: User){

        val intermediario: Intermediario = getIntermediario()
        var juego:Game
        try {
            juego = elegirJuego()
            usuario.realizarCompra(intermediario,juego)
        }catch (e:Exception){
            println(e.message)
        }

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

    fun elegirJuego(): Game {
        val idJuego: Long

        println("------JUEGOS DISPONIBLES-------")
        val listaDeJuegos: List<Game> = GameRepository.get()
        for(juego in listaDeJuegos){
            println(juego)
        }
        ///validarlo
        println("INGRESE EL ID DEL JUEGO QUE DESEE COMPRAR")
        idJuego = readln().toLong()
        if(!GameRepository.idValido(idJuego))throw RuntimeException("id incorrecto")
        return GameRepository.getById(idJuego)
    }
    fun menuElegirIntermediario():Int{

        var op: Int
        do{
            println("Elige un intermediario")
            println("1-Steam")
            println("2-EpicGames")
            println("3-Nakama")
            op = readln().toInt()
            if(op <1 && op >3)
                println("codigo incorrecto intentelo de nuevo")
        }while(op < 1 && op > 3)
        return op
    }
    fun getIntermediario():Intermediario {

        val intermediarioElegido = menuElegirIntermediario()
        val intermediario: Intermediario

        if(intermediarioElegido==1){
            intermediario = Steam()
        }else if(intermediarioElegido == 2){
            intermediario = EpicGames()
        }else{
            intermediario = Nakama()
        }
        return intermediario
    }
}
class saldont() : Exception(){
    override val message: String
        get() = "saldo insuficiente"
}
