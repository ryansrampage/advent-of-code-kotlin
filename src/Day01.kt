val map = mapOf("one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
)
fun part1(input: List<String>): Int {
    return input.sumOf { number -> (number.first { char -> char.isDigit() } + number.last { int -> int.isDigit() }.toString()).toInt() }
}

fun part2(input: List<String>): Int {
    return input.sumOf { number -> (map[number.findAnyOf(map.keys)?.second].toString() + map[number.findLastAnyOf(map.keys)?.second]!!).toInt() }
}
fun main() {

    val testToggle = false

    if (testToggle) {
        val testInput = readInput("Day01_test")
        println(part2(testInput))
        check(part2(testInput) == 281)
    }
    else {
        val input = readInput("Day01")
        part1(input).println()
        part2(input).println()
    }
}
