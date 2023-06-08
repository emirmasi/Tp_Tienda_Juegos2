package repositories

import data.Purchase

object  PurchaseRepository {

    private val purchases = mutableListOf<Purchase>()

    init {
        purchases.add(Purchase(1L, 1504L, 1L, 350.50, "2023/01/01"))
        purchases.add(Purchase(2L, 1504L, 4L, 100.75, "2023/01/01"))
        purchases.add(Purchase(3L, 1504L, 7L, 250.50, "2023/01/01"))
        purchases.add(Purchase(4L, 1504L, 10L, 50.00, "2023/01/01"))
        purchases.add(Purchase(5L, 1504L, 13L, 1350.15, "2023/01/01"))
        purchases.add(Purchase(6L, 2802L, 2L, 20.30, "2023/01/01"))
        purchases.add(Purchase(7L, 2802L, 9L, 450.75, "2023/01/01"))
        purchases.add(Purchase(8L, 2802L, 11L, 500.00, "2023/01/01"))
        purchases.add(Purchase(9L, 1510L, 3L, 350.50, "2023/01/01"))
        purchases.add(Purchase(10L, 1510L, 5L, 150.00, "2023/01/01"))
    }

    fun add(purchase: Purchase) {

        purchases.add(purchase)
    }

    fun get() : List<Purchase> {
            return purchases
    }

    fun getLastId(): Long{
        val ultimo = purchases.last()
        return ultimo.id+1;
    }

    fun mostrarHistorialDeCompra(id: Long?){

        val historialDeCompra: List<Purchase> = get().filter { it.userId == id }

        if(historialDeCompra.isEmpty()){
            println("no posee un historial de compra")
        }else{
            for(compra in historialDeCompra){
                println(compra)
            }
        }

    }
}