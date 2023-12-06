package day3

import java.io.File

fun main() {
    val matrix = File("./inputs/day-3.txt").readLines().map { it.toCharArray() }.toTypedArray()
    val list = mutableListOf<Int>()
    for (row in matrix.indices) {
        var numberStr = ""
        for (column in matrix[row].indices) {
            if (matrix[row][column].isDigit()) {
                numberStr += matrix[row][column]
                if (column == matrix[row].size - 1 && hasSymbolAround(row, column - numberStr.length, numberStr.length, matrix)) {
                    list.add(numberStr.toInt())
                }
            } else if (numberStr.isNotEmpty()) {
                if (hasSymbolAround(row, column - numberStr.length, numberStr.length, matrix)) {
                    list.add(numberStr.toInt())
                }
                numberStr = ""
            }
        }
    }
    println(list.sum())
}

private fun hasSymbolAround(row: Int, column: Int, length: Int, matrix: Array<CharArray>): Boolean {
    for (i in (row - 1).notBelow(0)..(row + 1).notAbove(matrix.size - 1)) {
        for (j in (column - 1).notBelow(0)..(column + length).notAbove(matrix[i].size - 1)) {
            if (matrix[i][j].isSymbol()) {
                return true
            }
        }
    }
    return false
}

private fun Char.isSymbol(): Boolean = !this.isDigit() && this != '.'
private fun Int.notBelow(minValue: Int) = this.coerceAtLeast(minValue)
private fun Int.notAbove(maxValue: Int) = this.coerceAtMost(maxValue)