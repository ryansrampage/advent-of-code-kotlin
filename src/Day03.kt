fun main() {
    fun getItems(input: List<String>): List<Item>
    {
        var itemList: List<Item> = mutableListOf()

        input.forEachIndexed { rowIndex, line ->
            var char = ""
            var col = 0
            line.forEachIndexed { columnIndex, column ->

                if (column.isDigit()) {
                    if (char.isBlank()) {
                        col = columnIndex
                    }
                    char += (column.toString())
                }
                else if (column.toString() != "." && !column.isDigit()) {
                    itemList.addLast(Symbol(columnIndex, rowIndex, column.toString()))

                    if (char.isNotBlank()) {
                        itemList.addLast(Number(char, char.length, col, rowIndex))
                        char = ""
                    }
                }
                else {
                    if (char.isNotEmpty()) {
                        itemList.addLast(Number(char, char.length, col, rowIndex))
                        char = ""
                    }
                }

                if (columnIndex == line.length - 1 && char.isNotEmpty()) {
                    itemList.addLast(Number(char, char.length, col, rowIndex))
                }
            }
        }

        return itemList
    }

    fun part1(input: List<String>): Int {
        var itemList = getItems(input)
        var numberList = itemList.filterIsInstance<Number>()
        var symbolList = itemList.filterIsInstance<Symbol>()
        var sum = 0

        for (number in numberList) {
            var symbolInBoundsXRange = number.initialColumnValue - 1 .. number.initialColumnValue + number.length
            var symbolInBoundsYRange = number.rowValue - 1 .. number.rowValue + 1

            for (symbol in symbolList) {
                if (symbolInBoundsXRange.contains(symbol.columnValue) && symbolInBoundsYRange.contains(symbol.rowValue)) {
                    sum += number.num.toInt()
                    break
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var itemList = getItems(input)
        var numberList = itemList.filterIsInstance<Number>()
        var symbolList = itemList.filterIsInstance<Symbol>()
        var sum = 0

        for (symbol in symbolList) {
            if (symbol.symbolType == "*") {
                var matchingNums = numberList.filter { symbol.columnValue in it.initialColumnValue - 1 .. it.initialColumnValue + it.length
                        && symbol.rowValue in it.rowValue - 1 .. it.rowValue + 1}

                if (matchingNums.size == 2) {
                    sum += matchingNums[0].num.toInt() * matchingNums[1].num.toInt()
                }
            }
        }
        return sum
    }

    val testToggle = false

    if (testToggle) {
        val testInput = readInput("Day03_test")
        println(part2(testInput))
        check(part2(testInput) == 467835)
    }
    else {
        val input = readInput("Day03")
        part1(input).println()
        part2(input).println()
    }
}

sealed class Item
data class Number(var num: String, var length: Int, var initialColumnValue: Int, var rowValue: Int) : Item()
data class Symbol(var columnValue: Int, var rowValue: Int, var symbolType: String) : Item()