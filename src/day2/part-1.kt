package day2

import java.io.File

const val MAX_REDS = 12
const val MAX_GREENS = 13
const val MAX_BLUES = 14

fun main() {
    val invalidGames = HashSet<Int>()
    File("./inputs/day-2.txt").forEachLine { line ->
        val (gameIdentifier, game) = line.split(":")
        val id = gameIdentifier.split(" ")[1]
        game.split(";").forEach { round ->
            round.split(",").forEach {
                val (total, color) = it.trim().split(" ")
                if (color == "red" && total.toInt() > MAX_REDS) {
                    invalidGames.add(id.toInt())
                } else if (color == "green" && total.toInt() > MAX_GREENS) {
                    invalidGames.add(id.toInt())
                } else if (color == "blue" && total.toInt() > MAX_BLUES) {
                    invalidGames.add(id.toInt())
                }
            }
        }
    }
    println((1..100).sum() - invalidGames.sum())
}