package day4

import java.io.File
import java.util.Stack

data class ScratchCard(
    val number: Int,
    val winningNumbers: List<Int>,
    val numbersYouHave: List<Int>
)

fun main() {
    val map = HashMap<Int, ScratchCard>()
    val stack = Stack<ScratchCard>()
    var totalCardsProcessed = 0

    File("./inputs/day-4.txt").forEachLine { line ->
        val (card, cardContent) = line.split(":")
        val cardNumber = card.split(" ").last().toInt()
        val (winningNumbers, numbersYouHave) = cardContent.split("|").map { allNumbers ->
            allNumbers.trim().split(" ")
                .filterNot { numberStr -> numberStr.isBlank() }
                .map { numberStr -> numberStr.toInt() }
        }

        ScratchCard(cardNumber, winningNumbers, numbersYouHave).also {
            map[cardNumber] = it
            stack.add(it)
        }
    }

    while (stack.isNotEmpty()) {
        val scratchCard = stack.pop()
        var nextScratchCardNumber = scratchCard.number + 1
        scratchCard.numbersYouHave.forEach { n ->
            if (scratchCard.winningNumbers.contains(n)) {
                stack.push(map[nextScratchCardNumber])
                nextScratchCardNumber++
            }
        }
        totalCardsProcessed++
    }
    println(totalCardsProcessed)
}

