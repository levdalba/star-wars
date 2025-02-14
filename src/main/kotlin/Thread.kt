//import java.util.concurrent.Semaphore
//import java.util.concurrent.atomic.AtomicInteger
//
//fun makeCoffee(orders: AtomicInteger, semaphore: Semaphore) {
//    while (orders.get() > 0) {
//        try {
//            semaphore.acquire()
//            if (orders.get() > 0) {
//                println("${Thread.currentThread().name} is making your coffee.")
//                Thread.sleep(5000)
//                println("Your coffee is ready. Enjoy!")
//                orders.decrementAndGet()
//            }
//        } catch (e: InterruptedException) {
//            println("An error occurred: ${e.message}")
//        } finally {
//            semaphore.release()
//        }
//    }
//}
//
//fun main() {
//    val orders = AtomicInteger(10) // Set initial number of orders
//    val semaphore = Semaphore(1) // Only one permit for the coffee machine
//
//    val barista1 = Thread {
//        makeCoffee(orders, semaphore)
//    }
//    barista1.name = "Barista 1"
//
//    val barista2 = Thread {
//        makeCoffee(orders, semaphore)
//    }
//    barista2.name = "Barista 2"
//
//    barista1.start()
//    barista2.start()
//
//    barista1.join()
//    barista2.join()
//}