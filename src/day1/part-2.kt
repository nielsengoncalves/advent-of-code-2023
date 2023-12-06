package day1

import java.io.File
import java.lang.RuntimeException

fun main() {
    var calibrationValuesSum = 0
    File("./inputs/day-1.txt").forEachLine { line ->
        val calibrationValue = findCalibrationValue(line)
        println("$line-$calibrationValue")
        calibrationValuesSum += calibrationValue
    }
    println(calibrationValuesSum)
}

private fun findCalibrationValue(line: String): Int {
    val leftDigit = findDigit(line, fromLeft = true)
    val rightDigit = findDigit(line, fromLeft = false)
    return "$leftDigit$rightDigit".toInt()
}

private fun findDigit(line: String, fromLeft: Boolean): Int {
    val numberWords = mapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4,
        "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
    )
    if (fromLeft) {
        line.forEachIndexed { index, char ->
            if (char.isDigit()) return char.toString().toInt()
            numberWords.forEach { (word, number) ->
                if (index <= line.length - word.length && line.substring(index, index + word.length) == word) {
                    return number
                }
            }
        }
    } else {
        for (index in line.length - 1 downTo 0) {
            if (line[index].isDigit()) return line[index].toString().toInt()
            numberWords.forEach { (word, number) ->
                if (index >= word.length - 1 && line.substring(index - word.length + 1, index + 1) == word) {
                    return number
                }
            }
        }
    }
    throw RuntimeException("Digit not found in line: $line")
}
