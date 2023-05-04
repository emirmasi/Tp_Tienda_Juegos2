package src.main.kotlin

abstract class Intermediario() {

    abstract fun aplicarComision(amount: Double):Double
     fun aplicarDescuento(createdDate: String, amount:Double):Double {
         val fecha = Fecha(createdDate)
         val cantMesesRegistrado = fecha.calcularDiferenciaDeMeses()
         if(cantMesesRegistrado <= 3){
              return amount - (amount * 0.05)
         }else if(cantMesesRegistrado in 4..12){
              return amount - (amount * 0.03)
         }else {
              return amount;
         }
     }

}


