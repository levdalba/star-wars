//enum class Fraction {
//    JEDI, SITH, REBEL, IMPERIAL
//}
//
//data class StarWarsCharacter(
//    val name: String,
//    val fraction: Fraction
//)
//
//data class MatchResult(
//    val character1: StarWarsCharacter,
//    val character2: StarWarsCharacter,
//    val score: Int = 0
//)
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
//fun main() {
//    val lightSide = characters.filter { it.fraction in setOf(Fraction.JEDI, Fraction.REBEL) }
//    val darkSide = characters.filterNot { it.fraction in setOf(Fraction.JEDI, Fraction.REBEL) }
//
//    val results = createPairs(
//        fraction1 = lightSide,
//        fraction2 = darkSide
//    ).matchWithFlatResults()
//
//}