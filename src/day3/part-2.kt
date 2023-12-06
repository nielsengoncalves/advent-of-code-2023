package day3

import java.io.File

data class StarCoordinate(val i: Int, val j: Int)

fun main() {
    val matrix = File("./inputs/day-3.txt").readLines().map { it.toCharArray() }.toTypedArray()
    val map = HashMap<StarCoordinate, MutableList<Int>>()
    for (row in matrix.indices) {
        var numberStr = ""
        for (column in matrix[row].indices) {
            if (matrix[row][column].isDigit()) {
                numberStr += matrix[row][column]
                if (column == matrix[row].size - 1) {
                    findStarsAround(row, column - numberStr.length, numberStr.length, matrix).forEach { key ->
                        map[key] = map[key]?.also { list -> list.add(numberStr.toInt()) } ?: mutableListOf(numberStr.toInt())
                    }
                }
            } else if (numberStr.isNotEmpty()) {
                findStarsAround(row, column - numberStr.length, numberStr.length, matrix).forEach { key ->
                    map[key] = map[key]?.also { list -> list.add(numberStr.toInt()) } ?: mutableListOf(numberStr.toInt())
                }
                numberStr = ""
            }
        }
    }
    val sum = map.filter { it.value.size == 2 }
        .map { it.value.first() * it.value.last() }
        .sum()
    println(sum)
}

private fun findStarsAround(row: Int, column: Int, length: Int, matrix: Array<CharArray>): List<StarCoordinate> {
    val list = mutableListOf<StarCoordinate>()
    for (i in (row - 1).notBelow(0)..(row + 1).notAbove(matrix.size - 1)) {
        for (j in (column - 1).notBelow(0)..(column + length).notAbove(matrix[i].size - 1)) {
            if (matrix[i][j] == '*') {
                list.add(StarCoordinate(i,j))
            }
        }
    }
    return list
}

private fun Int.notBelow(minValue: Int) = this.coerceAtLeast(minValue)
private fun Int.notAbove(maxValue: Int) = this.coerceAtMost(maxValue)