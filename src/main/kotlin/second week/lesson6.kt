package `second week`//package lesson6////    for (index in indices) {
//////        if (index < 0 || index >= array.size) {
//////            error("index out of bounds")
//////fun updateIndex(array: Array<String>, vararg indices: Int, func: (String) -> String) {
//////        }
//////        array[index] = func(array[index])
//////    }
//////}
//////
//////fun main() {
//////    val array = arrayOf("a", "b", "c", "d", "e")
//////    updateIndex(array, 0, 7) { it.uppercase() }
//////    println(array.joinToString(", "))
//////}
////
//////fun Int.isEven(): Boolean {
//////    return this % 2 == 0
//////}
//////
//////fun Int.isOdd(): Boolean {
//////    isEven().let {
//////        return !it
//////    }
//////}
//////
//////fun intCheck(){
//////    val number = 4
//////    println(number.isEven())
//////    println(number.isOdd())
//////}
//////
//////fun Array<String>.repeatTwice(): Array<String> {
//////    return this + this
//////}
//////
//////fun arrayExtension(){
//////    val array = arrayOf("a", "b", "c")
//////    val repeatedArray = array.repeatTwice()
//////    println(repeatedArray.joinToString(", "))
//////}
//////
//////fun main(){
//////    intCheck()
//////    arrayExtension()
//////}
////
////
//////data class Person(
//////    val name: String,
//////    val contact: Contact
//////)
//////
//////data class Contact(
//////    val email: String? = null,
//////    val phone: String? = null
//////)
//////
//////val person = Person(
//////    name = "John",
//////    contact = Contact()
//////)
//////
//////fun main() {
//////    val updatedPerson = person.copy(
//////        contact = person.contact.let { contact ->
//////                contact.copy(email = "123@gmail.com", phone = "1234567890")})
//////    println(updatedPerson)
//////}
////
//////data class StarWarsCharacter(
//////    val name: String,
//////    val fraction: Fraction
//////)
//////
//////enum class Fraction {
//////    JEDI, SITH, REBEL, IMPERIAL
//////}
//////
//////fun String.toStarWarsCharacter(fraction: Fraction): StarWarsCharacter {
//////    return StarWarsCharacter(this, fraction)
//////}
//////
//////fun List<String>.toStarWarsCharacters(fraction: Fraction): List<StarWarsCharacter> {
//////    return this.map { it.toStarWarsCharacter(fraction) }
//////}
//////
//////val jediNames = listOf("Qui-Gon Jinn", "Obi-Wan Kenobi", "Luke Skywalker")
//////val sitNames = listOf("Darth Maul", "Darth Vader", "Darth Sidious")
//////val rebelNames = listOf("Leia Organa", "Han Solo", "Luke Skywalker")
//////val imperialNames = listOf("Wilhuff Tarkin", "Sheev Palpatine", "Darth Vader")
//////
//////val jediCharacters = jediNames.toStarWarsCharacters(Fraction.JEDI)
//////val sithCharacters = sitNames.toStarWarsCharacters(Fraction.SITH)
//////val rebelCharacters = rebelNames.toStarWarsCharacters(Fraction.REBEL)
//////val imperialCharacters = imperialNames.toStarWarsCharacters(Fraction.IMPERIAL)
//////
//////fun main() {
//////    println(jediCharacters)
//////    println(sithCharacters)
//////    println(rebelCharacters)
//////    println(imperialCharacters)
//////}
////
////import kotlin.random.Random
////
////val jediNames = listOf("Qui-Gon Jinn", "Obi-Wan Kenobi", "Luke Skywalker")
////val sitNames = listOf("Darth Maul", "Darth Vader", "Darth Sidious")
////
////data class StarWarsCharacter(
////    val name: String,
////    val fraction: Fraction
////)
////
////enum class Fraction {
////    JEDI, SITH, REBEL, IMPERIAL
////}
////
////fun String.toStarWarsCharacter(fraction: Fraction): StarWarsCharacter {
////    return StarWarsCharacter(this, fraction)
////}
////
////fun List<String>.toStarWarsCharacters(fraction: Fraction): List<StarWarsCharacter> {
////    return this.map { it.toStarWarsCharacter(fraction) }
////}
////
////fun List<StarWarsCharacter>.pairWith(other: List<StarWarsCharacter>): List<Pair<StarWarsCharacter, StarWarsCharacter?>> {
////    return this.mapIndexed { index, character ->
////        if (index < other.size) {
////            character to other[index]
////        } else {
////            character to null
////        }
////    }
////}
////
////fun round(pair: Pair<StarWarsCharacter, StarWarsCharacter?>): StarWarsCharacter? {
////    return pair.second?.let {
////        if (Random.nextBoolean()) pair.first else it
////    }
////}
////
////fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.joinToString(): String {
////    return this.joinToString("\n") { (first, second) ->
////        "Fight between ${first.name} and ${second?.name}"
////    }
////}
////
////fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.performMatches(): List<Pair<Int, List<Pair<StarWarsCharacter, Int>>>> {
////    return this.mapIndexed { roundNumber, pair ->
////        val results = mutableListOf<Pair<StarWarsCharacter, Int>>()
////        (1..3).map {
////            val winner = round(pair)
////            if (winner != null) {
////                val existingResult = results.find { it.first == winner }
////                if (existingResult != null) {
////                    results[results.indexOf(existingResult)] = winner to (existingResult.second + 1)
////                } else {
////                    results.add(winner to 1)
////                }
////            }
////        }
////        roundNumber to results
////    }
////}
////
////fun third() {
////    val jediCharacters = jediNames.toStarWarsCharacters(Fraction.JEDI)
////    val sithCharacters = sitNames.toStarWarsCharacters(Fraction.SITH)
////
////    val pairedCharacters = jediCharacters.pairWith(sithCharacters)
////    println(pairedCharacters.joinToString())
////
////    val matchResults = pairedCharacters.performMatches()
////    matchResults.forEach { (roundNumber, results) ->
////        println("Round $roundNumber results:")
////        results.forEach { (character, score) ->
////            println("${character.name} scored $score")
////        }
////    }
////}
////
////fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.matchWithFlatResult(): List<Pair<StarWarsCharacter, Int>> {
////    val matchResults = this.performMatches()
////    return matchResults.flatMap { it.second }
////}
////
////fun matchWithFlatResult(): List<Pair<StarWarsCharacter, Int>> {
////    val jediCharacters = jediNames.toStarWarsCharacters(Fraction.JEDI)
////    val sithCharacters = sitNames.toStarWarsCharacters(Fraction.SITH)
////
////    val pairedCharacters = jediCharacters.pairWith(sithCharacters)
////    println(pairedCharacters.joinToString())
////    val matchResults = pairedCharacters.performMatches()
////    return matchResults.flatMap { it.second }
////}
////
////val characters = listOf(
////    StarWarsCharacter("Luke Skywalker", Fraction.JEDI),
////    StarWarsCharacter("Yoda", Fraction.JEDI),
////    StarWarsCharacter("Obi-Wan Kenobi", Fraction.JEDI),
////    StarWarsCharacter("Mace Windu", Fraction.JEDI),
////    StarWarsCharacter("Qui-Gon Jinn", Fraction.JEDI),
////    StarWarsCharacter("Darth Vader", Fraction.SITH),
////    StarWarsCharacter("Emperor Palpatine", Fraction.SITH),
////    StarWarsCharacter("Darth Maul", Fraction.SITH),
////    StarWarsCharacter("Kylo Ren", Fraction.SITH),
////    StarWarsCharacter("Count Dooku", Fraction.SITH),
////    StarWarsCharacter("Leia Organa", Fraction.REBEL),
////    StarWarsCharacter("Han Solo", Fraction.REBEL),
////    StarWarsCharacter("Chewbacca", Fraction.REBEL),
////    StarWarsCharacter("C3PO", Fraction.REBEL),
////    StarWarsCharacter("R2D2", Fraction.REBEL),
////    StarWarsCharacter("Stormtrooper", Fraction.IMPERIAL),
////    StarWarsCharacter("Imperial Officer", Fraction.IMPERIAL),
////    StarWarsCharacter("Imperial Guard", Fraction.IMPERIAL),
////    StarWarsCharacter("Death Trooper", Fraction.IMPERIAL),
////    StarWarsCharacter("TIE Fighter Pilot", Fraction.IMPERIAL)
////)
////
////fun createPairs(fraction1: List<StarWarsCharacter>, fraction2: List<StarWarsCharacter>): List<Pair<StarWarsCharacter, StarWarsCharacter?>> {
////    return fraction1.mapIndexed { index, character ->
////        if (index < fraction2.size) {
////            character to fraction2[index]
////        } else {
////            character to null
////        }
////    }
////}
////
////fun main() {
////    val jediCharacters = characters.filter { it.fraction in setOf(Fraction.JEDI, Fraction.REBEL) }
////    val sithCharacters = characters.filterNot { it.fraction in setOf(Fraction.JEDI, Fraction.REBEL) }
////
////    val results = createPairs(
////        fraction1 = jediCharacters,
////        fraction2 = sithCharacters
////    ).matchWithFlatResult()
////
////    // Group by character
////    val groupedByCharacter = results.groupBy { it.first }
////    val sortedByCharacter = groupedByCharacter
////        .mapValues { entry -> entry.value.sumOf { it.second } }
////        .toList()
////        .sortedByDescending { it.second }
////        .toMap()
////
////// Group by fraction
////    val groupedByFraction = results.groupBy { it.first.fraction }
////    val sortedByFraction = groupedByFraction
////        .mapValues { entry -> entry.value.sumOf { it.second } }
////        .toList()
////        .sortedByDescending { it.second }
////        .toMap()
////
////    // Print results
////    println("Sum of scores by character:")
////    sortedByCharacter.forEach { (character: StarWarsCharacter, sum: Int) ->
////        println("${character.name}: $sum")
////    }
////
////    println("\nSum of scores by fraction:")
////    sortedByFraction.forEach { (fraction: Fraction, sum: Int) ->
////        println("$fraction: $sum")
////    }
////}
//
//
////import kotlin.random.Random
////
////val jediNames = listOf("Qui-Gon Jinn", "Obi-Wan Kenobi", "Luke Skywalker")
////val sitNames = listOf("Darth Maul", "Darth Vader", "Darth Sidious")
////
////data class StarWarsCharacter(
////    val name: String,
////    val fraction: Fraction
////)
////
////enum class Fraction {
////    JEDI, SITH, REBEL, IMPERIAL
////}
////
////fun String.toStarWarsCharacter(fraction: Fraction): StarWarsCharacter {
////    return StarWarsCharacter(this, fraction)
////}
////
////fun List<String>.toStarWarsCharacters(fraction: Fraction): List<StarWarsCharacter> {
////    return this.map { it.toStarWarsCharacter(fraction) }
////}
////
////fun List<StarWarsCharacter>.pairWith(other: List<StarWarsCharacter>): List<Pair<StarWarsCharacter, StarWarsCharacter?>> {
////    return this.mapIndexed { index, character ->
////        if (index < other.size) {
////            character to other[index]
////        } else {
////            character to null
////        }
////    }
////}
////
////fun round(pair: Pair<StarWarsCharacter, StarWarsCharacter?>): StarWarsCharacter? {
////    return pair.second?.let {
////        if (Random.nextBoolean()) pair.first else it
////    }
////}
////
////fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.joinToString(): String {
////    return this.joinToString("\n") { (first, second) ->
////        "Fight between ${first.name} and ${second?.name}"
////    }
////}
////
////fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.performMatches(): List<Pair<Int, List<Pair<StarWarsCharacter, Int>>>> {
////    return this.mapIndexed { roundNumber, pair ->
////        val results = mutableListOf<Pair<StarWarsCharacter, Int>>()
////        (1..3).map {
////            val winner = round(pair)
////            if (winner != null) {
////                val existingResult = results.find { it.first == winner }
////                if (existingResult != null) {
////                    results[results.indexOf(existingResult)] = winner to (existingResult.second + 1)
////                } else {
////                    results.add(winner to 1)
////                }
////            }
////        }
////        roundNumber to results
////    }
////}
////
////fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.matchWithFlatResult(): List<Pair<StarWarsCharacter, Int>> {
////    val matchResults = this.performMatches()
////    return matchResults.flatMap { it.second }
////}
////
////val characters = listOf(
////    StarWarsCharacter("Luke Skywalker", Fraction.JEDI),
////    StarWarsCharacter("Yoda", Fraction.JEDI),
////    StarWarsCharacter("Obi-Wan Kenobi", Fraction.JEDI),
////    StarWarsCharacter("Mace Windu", Fraction.JEDI),
////    StarWarsCharacter("Qui-Gon Jinn", Fraction.JEDI),
////    StarWarsCharacter("Darth Vader", Fraction.SITH),
////    StarWarsCharacter("Emperor Palpatine", Fraction.SITH),
////    StarWarsCharacter("Darth Maul", Fraction.SITH),
////    StarWarsCharacter("Kylo Ren", Fraction.SITH),
////    StarWarsCharacter("Count Dooku", Fraction.SITH),
////    StarWarsCharacter("Leia Organa", Fraction.REBEL),
////    StarWarsCharacter("Han Solo", Fraction.REBEL),
////    StarWarsCharacter("Chewbacca", Fraction.REBEL),
////    StarWarsCharacter("C3PO", Fraction.REBEL),
////    StarWarsCharacter("R2D2", Fraction.REBEL),
////    StarWarsCharacter("Stormtrooper", Fraction.IMPERIAL),
////    StarWarsCharacter("Imperial Officer", Fraction.IMPERIAL),
////    StarWarsCharacter("Imperial Guard", Fraction.IMPERIAL),
////    StarWarsCharacter("Death Trooper", Fraction.IMPERIAL),
////    StarWarsCharacter("TIE Fighter Pilot", Fraction.IMPERIAL)
////)
////
////fun main() {
////    val jediCharacters = characters.filter { it.fraction == Fraction.JEDI }
////    val sithCharacters = characters.filter { it.fraction == Fraction.SITH }
////
////    // Using zip to create a list of pairs of characters
////    val pairedCharacters = jediCharacters.zip(sithCharacters)
////
////    // Unzipping the list of pairs into two separate lists
////    val (unzippedJedi, unzippedSith) = pairedCharacters.unzip()
////
////    // Using zipWithNext just for fun
////    val zippedWithNext = jediCharacters.zipWithNext()
////
////    // Using groupBy, map, and reduce to create the scoreboard by character
////    val results = pairedCharacters.matchWithFlatResult()
////    val scoreboardByCharacter = results
////        .groupBy { it.first }
////        .map { (character, scores) -> character to scores.map { it.second }.reduce { acc, score -> acc + score } }
////        .toMap()
////
////    // Using fold to create the scoreboard by fraction
////    val scoreboardByFraction = results
////        .groupBy { it.first.fraction }
////        .mapValues { (_, scores) -> scores.fold(0) { acc, pair -> acc + pair.second } }
////
////    // Print results
////    println("Scoreboard by character:")
////    scoreboardByCharacter.forEach { (character, score) ->
////        println("${character.name}: $score")
////    }
////
////    println("\nScoreboard by fraction:")
////    scoreboardByFraction.forEach { (fraction, score) ->
////        println("$fraction: $score")
////    }
////}
//
//import kotlin.random.Random
//
//val jediNames = listOf("Qui-Gon Jinn", "Obi-Wan Kenobi", "Luke Skywalker")
//val sitNames = listOf("Darth Maul", "Darth Vader", "Darth Sidious")
//
//data class StarWarsCharacter(
//    val name: String,
//    val fraction: Fraction
//)
//
//enum class Fraction {
//    JEDI, SITH, REBEL, IMPERIAL
//}
//
//fun String.toStarWarsCharacter(fraction: Fraction): StarWarsCharacter {
//    return StarWarsCharacter(this, fraction)
//}
//
//fun List<String>.toStarWarsCharacters(fraction: Fraction): List<StarWarsCharacter> {
//    return this.map { it.toStarWarsCharacter(fraction) }
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
//fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.joinToString(): String {
//    return this.joinToString("\n") { (first, second) ->
//        "Fight between ${first.name} and ${second?.name}"
//    }
//}
//
//fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.performMatches(rounds: Int): List<Pair<Int, List<Pair<StarWarsCharacter, Int>>>> {
//    return this.mapIndexed { roundNumber, pair ->
//        val results = mutableListOf<Pair<StarWarsCharacter, Int>>()
//        repeat(rounds) {
//            val winner = round(pair)
//            if (winner != null) {
//                val existingResult = results.find { it.first == winner }
//                if (existingResult != null) {
//                    results[results.indexOf(existingResult)] = winner to (existingResult.second + 1)
//                } else {
//                    results.add(winner to 1)
//                }
//            }
//        }
//        roundNumber to results
//    }
//}
//
//fun List<Pair<StarWarsCharacter, StarWarsCharacter?>>.matchWithFlatResult(rounds: Int): List<Pair<StarWarsCharacter, Int>> {
//    val matchResults = this.performMatches(rounds)
//    return matchResults.flatMap { it.second }
//}
//
//val characters = listOf(
//    StarWarsCharacter("Luke Skywalker", Fraction.JEDI),
//    StarWarsCharacter("Yoda", Fraction.JEDI),
//    StarWarsCharacter("Obi-Wan Kenobi", Fraction.JEDI),
//    StarWarsCharacter("Mace Windu", Fraction.JEDI),
//    StarWarsCharacter("Qui-Gon Jinn", Fraction.JEDI),
//    StarWarsCharacter("Darth Vader", Fraction.SITH),
//    StarWarsCharacter("Emperor Palpatine", Fraction.SITH),
//    StarWarsCharacter("Darth Maul", Fraction.SITH),
//    StarWarsCharacter("Kylo Ren", Fraction.SITH),
//    StarWarsCharacter("Count Dooku", Fraction.SITH),
//    StarWarsCharacter("Leia Organa", Fraction.REBEL),
//    StarWarsCharacter("Han Solo", Fraction.REBEL),
//    StarWarsCharacter("Chewbacca", Fraction.REBEL),
//    StarWarsCharacter("C3PO", Fraction.REBEL),
//    StarWarsCharacter("R2D2", Fraction.REBEL),
//    StarWarsCharacter("Stormtrooper", Fraction.IMPERIAL),
//    StarWarsCharacter("Imperial Officer", Fraction.IMPERIAL),
//    StarWarsCharacter("Imperial Guard", Fraction.IMPERIAL),
//    StarWarsCharacter("Death Trooper", Fraction.IMPERIAL),
//    StarWarsCharacter("TIE Fighter Pilot", Fraction.IMPERIAL)
//)
//
//fun main(args: Array<String>) {
//    val rounds = if (args.isNotEmpty()) {
//        args[0].toIntOrNull() ?: run {
//            println("Invalid number of rounds provided. Please enter a valid number.")
//            readLine()?.toIntOrNull() ?: run {
//                println("Invalid input. Exiting.")
//                return
//            }
//        }
//    } else {
//        println("Please enter the number of rounds:")
//        readLine()?.toIntOrNull() ?: run {
//            println("Invalid input. Exiting.")
//            return
//        }
//    }
//
//    val jediCharacters = characters.filter { it.fraction == Fraction.JEDI }
//    val sithCharacters = characters.filter { it.fraction == Fraction.SITH }
//
//    val pairedCharacters = jediCharacters.pairWith(sithCharacters)
//    println(pairedCharacters.joinToString())
//
//    val results = pairedCharacters.matchWithFlatResult(rounds)
//
//    val scoreboardByCharacter = results
//        .groupBy { it.first }
//        .map { (character, scores) -> character to scores.map { it.second }.reduce { acc, score -> acc + score } }
//        .toMap()
//
//    val scoreboardByFraction = results
//        .groupBy { it.first.fraction }
//        .mapValues { (_, scores) -> scores.fold(0) { acc, pair -> acc + pair.second } }
//
//    println("Scoreboard by character:")
//    scoreboardByCharacter.forEach { (character, score) ->
//        println("${character.name}: $score")
//    }
//
//    println("\nScoreboard by fraction:")
//    scoreboardByFraction.forEach { (fraction, score) ->
//        println("$fraction: $score")
//    }
//}