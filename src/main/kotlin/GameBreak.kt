package src.main.kotlin

import repositories.GameRepository
import repositories.PurchaseRepository
import repositories.UserRepository

import kotlin.RuntimeException
import java.time.*
import java.time.format.DateTimeFormatter


class GameBreak() {

    lateinit var usuario: data.User


    fun crearUsuario():Boolean{
        ///aca van a pedir los datos al usuario

        ///
        usuario = data.User(2L,"Emir","emirmasi12345","Isaias","Masi",200.0, "2020-03-02")
        UserRepository.addUser(usuario)

        return true
    }

    fun loguear(): Boolean{
        ///bloque try catch si es null manejar la excepcion nostros
        var nickname: String
        var password: String
        println("ingrese el nickName")
        nickname = readLine()!!.toString()
        println("ingrese el password")
        password = readLine()!!.toString()

        usuario = UserRepository.login(nickname,password)!!
        return true
    }

    fun realizarCompra(): Boolean{
        ///damos a elegir el intermediario usamos bloques when y hacemos la compr
        ///usar bloque try catch si el al usuario no le alcanza la plata mandamos un msj, manejamos nostros la excepcion
        val op:Int
        println("ingrese el intermediario :")
        println("1-Steam")
        println("2-EpicGames")
        println("3-Nakama")
        op = readLine()!!.toInt()
        //damos la opcion de elegir un juego

        val listaDejuego = GameRepository.get()
        println(listaDejuego)
        println("elije el id del juego que quiere comprar");
        val idJuego: Long = readLine()!!.toLong()
        val juego = GameRepository.getById(idJuego)
        try {
            var precioDelJuego = juego.price
            if(precioDelJuego > usuario.money)
                throw RuntimeException("no posee dinero suficiente para comprar este juego")
            when(op){
                1->{
                    val steam = Steam()
                    //aplicamos los descuentos y comisiones
                    precioDelJuego = steam.aplicarComision(precioDelJuego)
                    precioDelJuego = steam.aplicarDescuento(usuario.createdDate,precioDelJuego)
                }
                2->{
                    val epicGames = EpicGames()
                    precioDelJuego = epicGames.aplicarComision(precioDelJuego)
                    precioDelJuego = epicGames.aplicarDescuento(usuario.createdDate,precioDelJuego)
                }
                3->{
                    val nakama = Nakama()
                    precioDelJuego = nakama.aplicarComision(precioDelJuego)
                    precioDelJuego = nakama.aplicarDescuento(usuario.createdDate,precioDelJuego)
                }
            }
            ///agregamos la compra
            val hoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"))
            val compra = data.Purchase(PurchaseRepository.getLastId(),usuario.id,juego.id,precioDelJuego,hoy)
            PurchaseRepository.add(compra)
            return true
        }catch(e: RuntimeException){
            println(e.message)
            return false
        }

    }

    fun mostrarHistorialDeCompra(){
        ///mediante el id del usuario mostramos la listade compras que realizo
        ///añadir funcion de retornarHistorial en PurcharseRepository
        ///etc mostrar con un formato lindo
    }


}