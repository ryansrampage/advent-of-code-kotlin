import kotlin.math.pow

data class Ticket (val winningNumbers: List<Int>, val actualNumbers: List<Int>)


fun main() {

    fun getTickets(input: List<String>): List<Ticket> {
        return input.map { it.substringAfter(": ") }
                .map { it.split("|")
                        .map { it.split(" ")
                                .filter { it.isNotBlank()
                                }
                        }
                }.map { Ticket(it[0].map { it.toInt() }, it[1].map { it.toInt() }) }
    }

    fun getTotalAmountOfTickets(matches: MutableList<Int>): Int {
        var endList = matches.map { 1 }.toMutableList()

        return endList.mapIndexed { outerIndex, number ->
            for (innerIndex in 1 .. number) {
                for (number in outerIndex + 1 .. outerIndex + matches[outerIndex]) {
                    endList[number] += 1
                }
            }
        }.let { endList.sumOf { it } }
    }

    fun part1(input: List<String>): Int {
        return getTickets(input).map {
            ticket -> ticket.actualNumbers.map {
            value -> ticket.winningNumbers.contains(value)
        }
        }.map {
            match -> match.count { it }
        }.sumOf { 2.0.pow(it - 1).toInt() }
    }

    fun part2(input: List<String>): Int {
        return getTotalAmountOfTickets(getTickets(input).map {
            ticket -> ticket.actualNumbers.map {
            value -> ticket.winningNumbers.contains(value)
        }
        }.map {
            match -> match.count { it }
        }.toMutableList())
    }

    val testToggle = false

    if (testToggle) {
        val testInput = readInput("Day04_test")
        check(part1(testInput) == 13)
        check(part2(testInput) == 30)
    }
    else {
        val input = readInput("Day04")
        part1(input).println()
        part2(input).println()
    }
}
