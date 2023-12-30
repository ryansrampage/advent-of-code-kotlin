import kotlin.math.max

data class Game(val id: Int, val gameStack: List<List<Pair<String, Int>>>)
var limitMap = mapOf("blue" to 14, "green" to 13, "red" to 12)

fun validateGame(game: Game) : Boolean {
    for (round in game.gameStack) {
        for (pair in round) {
            if (pair.second > limitMap[pair.first]!!) {
                return false
            }
        }
    }
    return true
}

fun sumOfColoursInGames(game: Game) : Int {
    var red = 0
    var blue = 0
    var green = 0

    game.gameStack.map {
        it.forEach { pair ->
            if (pair.first == "red") { red = max(red, pair.second) }
            if (pair.first == "blue") { blue = max(blue, pair.second) }
            if (pair.first == "green") { green = max(green, pair.second) }
        }
    }

    return red * green * blue
}
fun main() {

    fun part1(input: List<String>): Int {
        return input.map { string -> string.substringAfter(": ") }
                .map {secondaryString -> secondaryString.split("; ") }
                .mapIndexed { index, it ->
                    it.map {
                        it.split(", ").map{
                            val (first, second) = it.split(" ")
                            Pair(second, first.toInt())
                        }
                    }.let { Game(index + 1, it) }
                }.filter { validateGame(it) }.sumOf { it.id }
    }

    fun part2(input: List<String>): Int {
        return input.map { string -> string.substringAfter(": ") }
                .map {secondaryString -> secondaryString.split("; ") }
                .mapIndexed { index, it ->
                    it.map {
                        it.split(", ").map{
                            val (first, second) = it.split(" ")
                            Pair(second, first.toInt())
                        }
                    }.let { Game(index + 1, it) }
                }.sumOf { sumOfColoursInGames(it) }
    }

    val testToggle = false

    if (testToggle) {
        val testInput = readInput("Day02_test")
        println(part2(testInput))
        check(part2(testInput) == 2286)
    }
    else {
        val input = readInput("Day02")
        part1(input).println()
        part2(input).println()
    }
}
