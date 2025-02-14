import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.random.Random

enum class MenuItem(val time: Long) {
    AMERICANO(200),
    ESPRESSO(100),
    DOUBLE_ESPRESSO(200),
    CAPPUCCINO(400),
    FLAT_WHITE(400),
}

class CoffeeShop(
    private val openDuration: Long,
    private val unit: ChronoUnit,
    val baristas: List<String>
) {
    private val ordersChannel = Channel<MenuItem>(capacity = 10)
    private val coffeeMachineMutex = Mutex()
    private val openingTime = Instant.now()
    private val closingTime = openingTime.plus(openDuration, unit)

    fun isOpen(): Boolean = Instant.now().isBefore(closingTime)

    fun placeOrder(order: MenuItem) {
        if (isOpen()) {
            GlobalScope.launch {
                ordersChannel.send(order)
                println("📌 Order placed: $order")
            }
        } else {
            println("🚫 Shop is closed! No more orders.")
        }
    }

    suspend fun processOrders(barista: String) {
        for (order in ordersChannel) {
            println("🕒 $barista is waiting for the coffee machine...")
            coffeeMachineMutex.withLock {
                println("☕ $barista is preparing ${order.name}")
                delay(order.time + Random.nextLong(50, 150))
                println("✅ $barista finished making ${order.name}")
            }
        }
    }

    fun closeShop() {
        GlobalScope.launch {
            delay(unit.duration.multipliedBy(openDuration).toMillis())
            ordersChannel.close()
            println("🏁 Coffee shop is now closed. Processing remaining orders...")
        }
    }
}

class OrderGenerator(private val coffeeShop: CoffeeShop) {
    suspend fun generateOrders() {
        while (coffeeShop.isOpen()) {
            delay(500)
            val order = MenuItem.values().random()
            coffeeShop.placeOrder(order)
        }
        println("🚪 Order generator stopped. Shop is closing.")
    }
}

fun main() = runBlocking {
    println("🚀 Coffee shop is OPEN!")

    val coffeeShop = CoffeeShop(10, ChronoUnit.SECONDS, listOf("Alice", "Bob"))
    val orderGenerator = OrderGenerator(coffeeShop)

    val baristaJobs = coffeeShop.baristas.map { barista ->
        launch { coffeeShop.processOrders(barista) }
    }

    val orderJob = launch { orderGenerator.generateOrders() }

    coffeeShop.closeShop()
    orderJob.join()
    baristaJobs.forEach { it.join() }

    println("🏁 Simulation complete!")
}