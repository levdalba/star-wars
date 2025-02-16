//import kotlin.random.Random
//
//enum class CharacterLevel(val points: Int) {
//    LEVEL_1(10),
//    LEVEL_2(20),
//    LEVEL_3(30),
//    LEVEL_4(40),
//    LEVEL_5(50),
//    LEVEL_6(60),
//    LEVEL_7(70),
//    LEVEL_8(80),
//    LEVEL_9(90),
//    LEVEL_10(100)
//}
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
//abstract class Character(
//    val name: String,
//    val fraction: Fraction,
//    val level: CharacterLevel,
//    var stamina: Int = 0,
//    var mana: Int = 0
//) : Recoverable {
//    init {
//        require(stamina + mana <= level.points) {
//            "Total points for stamina and mana should not exceed ${level.points}"
//        }
//    }
//
//    override fun beforeRounds() {
//        stamina += 1
//        mana += 1
//    }
//
//    override fun afterRound() {
//        // Optional: Add additional behavior after a round
//    }
//}
//
//class Warrior(
//    name: String,
//    fraction: Fraction,
//    level: CharacterLevel,
//    stamina: Int = 0,
//    mana: Int = 0
//) : Character(name, fraction, level, stamina, mana)
//
//class Sorcerer(
//    name: String,
//    fraction: Fraction,
//    level: CharacterLevel,
//    stamina: Int = 0,
//    mana: Int = 0
//) : Character(name, fraction, level, stamina, mana)
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
//    val jediCharacter = Warrior("Luke Skywalker", Fraction.JEDI, CharacterLevel.LEVEL_1, stamina = 5, mana = 5)
//    val sithCharacter = Sorcerer("Darth Vader", Fraction.SITH, CharacterLevel.LEVEL_1, stamina = 5, mana = 5)
//
//    val match = Match(rounds = 3, challenger = jediCharacter, opponent = sithCharacter)
//    val winner = match.fight()
//    println("The winner is: ${winner?.name}")
//}
