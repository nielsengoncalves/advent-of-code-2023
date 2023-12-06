package day4

import java.io.File

fun main() {
    var totalPoints = 0
    File("./inputs/day-4.txt").forEachLine { line ->
        val (winningNumbers, numbersYouHave) = line.split(":")[1].split("|").map { allNumbers ->
            allNumbers.trim().split(" ")
                .filterNot { numberStr -> numberStr.isBlank() }
                .map { numberStr -> numberStr.toInt() }
        }
        val totalPointsCard = numbersYouHave.fold(0) { acc, n ->
            if (winningNumbers.contains(n)) {
                if (acc == 0) acc + 1 else acc * 2
            } else acc
        }
        totalPoints += totalPointsCard
    }
    println(totalPoints)
}

