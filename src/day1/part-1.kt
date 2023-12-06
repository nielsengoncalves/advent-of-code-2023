package day1

import java.io.File

fun main() {
    var calibrationValuesSum = 0;
    File("./inputs/day-1.txt").forEachLine { line ->
        val chars = line.toCharArray()
        calibrationValuesSum += findCalibrationValue(chars)
    }
    println(calibrationValuesSum)
}

private fun findCalibrationValue(line: CharArray): Int {
    var left = 0
    var right = line.size - 1
    while (!(line[left].isDigit() && line[right].isDigit())) {
        if (!line[left].isDigit()) {
            left++
            continue
        }
        if (!line[right].isDigit()) {
            right--
            continue
        }
    }
    return "${line[left]}${line[right]}".toInt()
}