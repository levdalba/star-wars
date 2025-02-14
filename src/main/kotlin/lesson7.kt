//import io.github.oshai.kotlinlogging.KotlinLogging
//import java.io.File
//import java.io.IOException
//import kotlin.random.Random
//import kotlinx.serialization.*
//import kotlinx.serialization.json.*
//import lesson6.Fraction
//import lesson6.StarWarsCharacter
//
//private val logger = KotlinLogging.logger {}
//
//@Serializable
//data class StarWarsCharacter(
//    val name: String,
//    val fraction: Fraction
//)
//
//@Serializable
//enum class Fraction {
//    JEDI, SITH, REBEL, IMPERIAL
//}
//
//@Serializable
//data class BattleResult(
//    val character1: StarWarsCharacter,
//    val character2: StarWarsCharacter?,
//    val winners: List<StarWarsCharacter>
//)
//
//@Serializable
//data class BattleResultJson(
//    val character1: String,
//    val character2: String?,
//    val winners: List<String>
//)
//
//fun loadStarWarsCharacters(filePath: String): List<StarWarsCharacter> {
//    val characters = try {
//        val file = File(filePath)
//        file.bufferedReader().use { reader ->
//            reader.lineSequence().drop(1).map { line ->
//                val (name, fraction) = line.split(",").also { require(it.size == 2) { "validation error" } }
//                StarWarsCharacter(
//                    name = name.trim(),
//                    fraction = Fraction.valueOf(fraction.trim())
//                )
//            }.toList()
//        }
//    } catch (e: IOException) {
//        println("An error occurred: ${e.message}")
//        emptyList<StarWarsCharacter>()
//    }
//    return characters
//}
//
//fun List<StarWarsCharacter>.pairWith(other: List<StarWarsCharacter>): List<Pair<StarWarsCharacter, StarWarsCharacter?>> {
//    return this.mapIndexed { index, character ->
//        if (index < other.size) {
//            character to other[index]
//        } else {
//            character to null
//        }
//    }
//}
//
//fun round(pair: Pair<StarWarsCharacter, StarWarsCharacter?>): StarWarsCharacter? {
//    return pair.second?.let {
//        if (Random.nextBoolean()) pair.first else it
//    }
//}
//
//fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.performMatches(rounds: Int): List<BattleResult> {
//    return this.map { pair ->
//        val results = mutableListOf<StarWarsCharacter>()
//        repeat(rounds) {
//            val winner = lesson6.round(pair)
//            if (winner != null) {
//                results.add(winner)
//            }
//        }
//        BattleResult(pair.first, pair.second, results)
//    }
//}
//
//private val json1 = Json { prettyPrint = true }
//
//fun saveResults(results: List<BattleResult>, filePath: String): File {
//    val file = File(filePath)
//    try {
//        val jsonResults = results.map { result ->
//            BattleResultJson(
//                character1 = result.character1.name,
//                character2 = result.character2?.name,
//                winners = result.winners.map { it.name }
//            )
//        }
//        val json = Json { prettyPrint = true }.encodeToString(jsonResults)
//        file.writeText(json)
//    } catch (e: IOException) {
//        logger.error(e) { "Error writing results to file at $filePath" }
//    }
//    return file
//}
//
//fun main() {
//    val path = "src/main/resources/data.csv"
//    val resultsFilePath = "src/main/resources/results.json"
//
//    try {
//        val characters = loadStarWarsCharacters(path)
//        val jediCharacters = characters.filter { it.fraction == Fraction.JEDI }
//        val sithCharacters = characters.filter { it.fraction == Fraction.SITH }
//
//        val pairedCharacters = lesson6.pairWith(sithCharacters)
//        val results = pairedCharacters.performMatches(5)
//
//        val resultsFile = saveResults(results, resultsFilePath)
//        logger.info { "Results saved to: ${resultsFile.absolutePath}" }
//
//    } catch (e: IOException) {
//        logger.error(e) { "An error occurred: ${e.message}" }
//    }
//}