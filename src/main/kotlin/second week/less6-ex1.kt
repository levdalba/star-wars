package `second week`
//import kotlin.random.Random
//
//enum class Fraction {
//    JEDI, SITH
//}
//
//data class Character(
//    val name: String,
//    val fraction: Fraction
//)
//
//class Match(
//    val rounds: Int,
//    val challenger: Character,
//    val opponent: Character
//) {
//    fun fight(): Character? {
//        val results = mutableMapOf<Character, Int>()
//        repeat(rounds) {
//            val winner = if (Random.nextBoolean()) challenger else opponent
//            results[winner] = results.getOrDefault(winner, 0) + 1
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