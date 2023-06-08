package src.main.kotlin
import java.time.LocalTime

class EpicGames(): Intermediario() {

    private val fechaActual = LocalTime.now()//"yyyy-mm-dd: hh:mm:ss"
    private val hora = fechaActual.hour

    override fun aplicarComision(precio:Double):Double {
        if(hora<24 && hora>=20){
            return precio*1.01
        }else{
            return precio*1.03
        }
    }

}