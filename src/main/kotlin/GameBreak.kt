package src.main.kotlin

import repositories.UserRepository

class GameBreak() {

    lateinit var usuario: data.User
    lateinit var juego: data.Game
    lateinit var compra: data.Purchase

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
        ///damos a elegir el intermediario usamos bloques when y hacemos la compra
        ///usar bloque try catch si el al usuario no le alcanza la plata mandamos un msj, manejamos nostros la excepcion
        return true
    }

    fun mostrarHistorialDeCompra(){
        ///mediante el id del usuario mostramos la listade compras que realizo
        ///añadir funcion de retornarHistorial en PurcharseRepository
        ///etc mostrar con un formato lindo
    }


}