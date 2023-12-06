package day2

import java.io.File

fun main() {
    var powerSum = 0
    File("./inputs/day-2.txt").forEachLine { line ->
        val (_, game) = line.split(":")
        var maxRed = 0
        var maxGreen = 0
        var maxBlue = 0
        game.split(";").forEach { round ->
            round.split(",").forEach {
                val (total, color) = it.trim().split(" ")
                when (color) {
                    "red" -> maxRed = Math.max(maxRed, total.toInt())
                    "green" -> maxGreen = Math.max(maxGreen, total.toInt())
                    "blue" -> maxBlue = Math.max(maxBlue, total.toInt())
                }
            }
        }
        powerSum += (maxRed * maxGreen * maxBlue)
    }
    println(powerSum)
}