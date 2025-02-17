import kotlin.random.Random

// --- Domain Model ---

enum class CharacterLevel {
    LEVEL_1, LEVEL_2, LEVEL_3
}

sealed class Character {
    abstract val name: String
    abstract var health: Int
    abstract val attackPower: Int
    abstract val level: CharacterLevel
    // New property: fraction represents the universe or faction (e.g. "HarryPotter" or "StarWars")
    abstract val fraction: String
}

data class Warrior(
    override val name: String,
    override var health: Int,
    override val attackPower: Int,
    val stamina: Int,
    val defensePower: Int,
    override val level: CharacterLevel,
    override val fraction: String // e.g., "StarWars"
) : Character()

data class Sorcerer(
    override val name: String,
    override var health: Int,
    override val attackPower: Int,
    val mana: Int,
    val healingPower: Int,
    override val level: CharacterLevel,
    override val fraction: String // e.g., "HarryPotter"
) : Character()

// --- Matching Strategy ---

data class CharacterMatchingStrategy(
    val name: String? = null,
    val level: CharacterLevel? = null,
    // characterClass can be used to filter by the type name (e.g., "Warrior" or "Sorcerer")
    val characterClass: String? = null,
    // New filter for fraction (e.g., "HarryPotter" or "StarWars")
    val fraction: String? = null
) {
    companion object {
        val ANY = CharacterMatchingStrategy()
    }
}

// --- Repository (Simulated Database) ---

object CharacterRepository {

    private val harryPotterCharacters = listOf(
        Sorcerer(name = "Harry Potter", health = 100, attackPower = 40, mana = 30, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Hermione Granger", health = 90, attackPower = 40, mana = 40, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Ron Weasley", health = 120, attackPower = 50, mana = 20, healingPower = 10, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Severus Snape", health = 80, attackPower = 60, mana = 30, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Albus Dumbledore", health = 90, attackPower = 40, mana = 40, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Lord Voldemort", health = 80, attackPower = 80, mana = 10, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Minerva McGonagall", health = 100, attackPower = 40, mana = 30, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Bellatrix Lestrange", health = 80, attackPower = 70, mana = 20, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Draco Malfoy", health = 100, attackPower = 40, mana = 30, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter"),
        Sorcerer(name = "Neville Longbottom", health = 130, attackPower = 30, mana = 10, healingPower = 30, level = CharacterLevel.LEVEL_1, fraction = "HarryPotter")
    )

    private val starWarsCharacters = listOf(
        Warrior(name = "Luke Skywalker", health = 110, attackPower = 40, stamina = 20, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Yoda", health = 80, attackPower = 30, stamina = 50, defensePower = 40, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Han Solo", health = 120, attackPower = 40, stamina = 10, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Darth Vader", health = 100, attackPower = 60, stamina = 10, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Obi-Wan Kenobi", health = 100, attackPower = 40, stamina = 30, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Emperor Palpatine", health = 80, attackPower = 80, stamina = 10, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Mace Windu", health = 110, attackPower = 40, stamina = 20, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Darth Maul", health = 90, attackPower = 60, stamina = 20, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Kylo Ren", health = 100, attackPower = 50, stamina = 20, defensePower = 30, level = CharacterLevel.LEVEL_1, fraction = "StarWars"),
        Warrior(name = "Finn", health = 130, attackPower = 20, stamina = 10, defensePower = 40, level = CharacterLevel.LEVEL_1, fraction = "StarWars")
    )

    fun getCharacters(): List<Character> = harryPotterCharacters + starWarsCharacters
}

// --- Service Interfaces ---

interface CharacterService {
    fun findChallenger(strategy: CharacterMatchingStrategy? = null): Character
    fun findOpponent(challenger: Character, strategy: CharacterMatchingStrategy? = null): Character
}

interface MatchService {
    fun match(rounds: Int, matchingStrategy: CharacterMatchingStrategy? = null): MatchResult
}

// --- Service Implementations ---

class RandomCharacterService : CharacterService {

    override fun findChallenger(strategy: CharacterMatchingStrategy?): Character {
        val characters = filterCharacters(CharacterRepository.getCharacters(), strategy)
        return characters.random()
    }

    override fun findOpponent(challenger: Character, strategy: CharacterMatchingStrategy?): Character {
        val characters = filterCharacters(CharacterRepository.getCharacters(), strategy)
        // Exclude the challenger from potential opponents.
        val opponents = characters.filter { it != challenger }
        return opponents.random()
    }

    private fun filterCharacters(
        characters: List<Character>,
        strategy: CharacterMatchingStrategy?
    ): List<Character> {
        if (strategy == null || strategy == CharacterMatchingStrategy.ANY) return characters

        return characters.filter { character ->
            val matchesName = strategy.name?.let { character.name == it } ?: true
            val matchesLevel = strategy.level?.let { character.level == it } ?: true
            val matchesClass = strategy.characterClass?.let {
                character::class.simpleName == it
            } ?: true
            val matchesFraction = strategy.fraction?.let {
                character.fraction.equals(it, ignoreCase = true)
            } ?: true
            matchesName && matchesLevel && matchesClass && matchesFraction
        }
    }
}

data class MatchResult(
    val challenger: Character,
    val opponent: Character,
    val winner: Character?,
    val rounds: Int
)

class RandomMatchService(
    private val characterService: CharacterService
) : MatchService {

    override fun match(rounds: Int, matchingStrategy: CharacterMatchingStrategy?): MatchResult {
        val challenger = characterService.findChallenger(matchingStrategy)
        val opponent = characterService.findOpponent(challenger, matchingStrategy)
        val winner = simulateBattle(challenger, opponent, rounds)
        return MatchResult(challenger, opponent, winner, rounds)
    }

    private fun simulateBattle(challenger: Character, opponent: Character, rounds: Int): Character? {
        // Work on copies of health values.
        var challengerHealth = challenger.health
        var opponentHealth = opponent.health

        for (round in 1..rounds) {
            // Simulate simultaneous attacks.
            opponentHealth -= challenger.attackPower
            challengerHealth -= opponent.attackPower

            // Stop early if one (or both) has been defeated.
            if (challengerHealth <= 0 || opponentHealth <= 0) break
        }

        // Determine the winner (or a draw).
        return when {
            challengerHealth <= 0 && opponentHealth <= 0 -> null // Draw
            challengerHealth > opponentHealth -> challenger
            else -> opponent
        }
    }
}

// --- Game Class ---

class Game(private val matchService: MatchService) {
    fun playMatch(rounds: Int, matchingStrategy: CharacterMatchingStrategy? = null) {
        val result = matchService.match(rounds, matchingStrategy)
        println("Battle: ${result.challenger.name} [${result.challenger.fraction}] vs ${result.opponent.name} [${result.opponent.fraction}]")
        result.winner?.let {
            println("Winner: ${it.name} [${it.fraction}]")
        } ?: println("The match ended in a draw!")
    }
}

// --- Bonus: Factory Class for Creating a Preset Game ---

object GameFactory {
    fun createGame(): Game {
        val characterService = RandomCharacterService()
        val matchService = RandomMatchService(characterService)
        return Game(matchService)
    }
}

// --- Main Function ---

fun main() {
    val strategy = CharacterMatchingStrategy(
        fraction = "StarWars",
        level = CharacterLevel.LEVEL_1
    )

    val game = GameFactory.createGame()
    game.playMatch(rounds = 20, matchingStrategy = strategy)
}
