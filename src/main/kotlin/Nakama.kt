package src.main.kotlin

class Nakama() : Intermediario(){
    ///si la compra se realiza un sabado o un domigo se aplica una comision de 3% si no 0.075%
    private val dia: String=""

    override fun aplicarComision(precio: Double):Double {
        if(dia=="sabado"||dia=="domingo"){
            return precio*1.03
        }else{
            return precio*1.0075
            }
    }
}