package `second week`//import kotlin.random.Random
//
//enum class Fraction {
//    JEDI, SITH, REBEL, IMPERIAL
//}
//
//interface Recoverable {
//    fun beforeRounds()
//    fun afterRound()
//}
//
//open class Character(
//    val name: String,
//    val fraction: Fraction,
//    var stamina: Int = 0,
//    var mana: Int = 0
//) : Recoverable {
//    override fun beforeRounds() {
//        stamina += 1
//        mana += 1
//    }
//
//    override fun afterRound() {
////
//    }
//}
//
//class Warrior(
//    name: String,
//    fraction: Fraction,
//    stamina: Int = 0,
//    mana: Int = 0
//) : Character(name, fraction, stamina, mana)
//
//class Sorcerer(
//    name: String,
//    fraction: Fraction,
//    stamina: Int = 0,
//    mana: Int = 0
//) : Character(name, fraction, stamina, mana)
//
//class Match(
//    val rounds: Int,
//    val challenger: Character,
//    val opponent: Character
//) {
//    fun fight(): Character? {
//        challenger.beforeRounds()
//        opponent.beforeRounds()
//
//        val results = mutableMapOf<Character, Int>()
//        repeat(rounds) {
//            val winner = if (Random.nextBoolean()) challenger else opponent
//            results[winner] = results.getOrDefault(winner, 0) + 1
//            challenger.afterRound()
//            opponent.afterRound()
//        }
//        return results.maxByOrNull { it.value }?.key
//    }
//}
//
//fun main() {
//    val jediCharacter = Character("Luke Skywalker", Fraction.JEDI)
//    val sithCharacter = Character("Darth Vader", Fraction.SITH)
//
//    val match = Match(rounds = 3, challenger = jediCharacter, opponent = sithCharacter)
//    val winner = match.fight()
//    println("The winner is: ${winner?.name}")
//}