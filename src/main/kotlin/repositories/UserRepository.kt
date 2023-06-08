package repositories

import data.User
import src.main.kotlin.src.main.kotlin.repositories.hoyConMiFormato
import java.time.LocalDate

object UserRepository {

    private val users = mutableListOf<User>()
    lateinit var usuario:User

    init {
        users.add(User(1504L, "BRIAN_BAYARRI", "abc123", "Brian", "Bayarri", 350.50, "2022/12/10"))
        users.add(User(2802L, "AHOZ", "lock_password", "Aylen", "Hoz", 200.50, "2021/01/11"))
        users.add(User(1510L, "Diegote", "@12345", "Diego", "Gonzales", 12.0, "2018/04/15"))
    }

    fun login(nickName: String,password: String) : User? {
        return users.firstOrNull { user -> user.nickName == nickName && user.password == password }
    }
    fun loguear(): User?{///este es de user

        var nickname:String
        var password:String
        do{
            println("Ingrese el nickName:")
            nickname = readln()

            println("Ingrese el password:")
            password = readln()

            if(nickname.isBlank() || password.isBlank()){
                println("algun campo esta vacio,intentelo de nuevo")
            }
        }while(nickname.isBlank() || password.isBlank())


        return login(nickname,password)

    }
    fun addUser(newUser: User):Boolean{
       return  users.add(newUser)
    }
    fun getLastId():Long{
        val ultimo = users.last()
        return ultimo.id+1
    }
    fun crearUsuario():User{
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

        val hoy: LocalDate = LocalDate.now()

        usuario = User(getLastId(),nickname,password,name,surname,money,hoy.hoyConMiFormato())
        if(addUser(usuario)){
            println("se registro correctamente")
        }else{
            println("ocurrio un error al registrarse")
        }
        return usuario
    }


}