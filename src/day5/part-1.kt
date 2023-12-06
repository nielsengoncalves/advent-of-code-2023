package day5

import java.io.BufferedReader
import java.io.File
import java.lang.RuntimeException

fun main() {
    val reader = File("./inputs/day-5.txt").bufferedReader()
    val seeds = parseSeeds(reader)
    val seedToSoilRanges = parseRanges("seed-to-soil map:", reader)
    val soilToFertilizerRanges = parseRanges("soil-to-fertilizer map:", reader)
    val fertilizerToWaterRanges = parseRanges("fertilizer-to-water map:", reader)
    val waterToLightRanges = parseRanges("water-to-light map:", reader)
    val lightToTemperatureRanges = parseRanges("light-to-temperature map:", reader)
    val temperatureToHumidityRanges = parseRanges("temperature-to-humidity map:", reader)
    val humidityToLocationRanges = parseRanges("humidity-to-location map:", reader)

    val seedFinalLocations = seeds.map {
        seedToSoilRanges.getDestination(it)
            .let { soilToFertilizerRanges.getDestination(it) }
            .let { fertilizerToWaterRanges.getDestination(it) }
            .let { waterToLightRanges.getDestination(it) }
            .let { lightToTemperatureRanges.getDestination(it) }
            .let { temperatureToHumidityRanges.getDestination(it) }
            .let { humidityToLocationRanges.getDestination(it) }
    }

    println(seedFinalLocations.min())
}

fun parseSeeds(reader: BufferedReader): MutableList<Long> {
    val (label, seeds) = reader.readLine().split(":")
    if (label != "seeds") {
        throw RuntimeException("Expecting seeds block!")
    }
    return seeds.split(" ")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .map { it.toLong() }
        .toMutableList()
        .also { reader.readLine() }
}

data class Range(
    val startOrigin: Long,
    val startDestination: Long,
    val length: Long,
) {
    private fun contains(origin: Long): Boolean = origin in startOrigin..startOrigin + length
    fun findDestinationOrNull(origin: Long): Long? =
        if (contains(origin)) origin - startOrigin + startDestination else null
}

fun List<Range>.getDestination(origin: Long): Long =
    this.firstNotNullOfOrNull { range -> range.findDestinationOrNull(origin) } ?: origin

fun parseRanges(title: String, reader: BufferedReader): List<Range> {
    val firstLine = reader.readLine()
    val rangeList = mutableListOf<Range>()
    if (firstLine != title) {
        throw RuntimeException("Expecting `$title` block!")
    }

    while (true) {
        val line = reader.readLine()
        if (line == null || line.isBlank()) break
        val (startDestination, startOrigin, length) = line.split(" ").map { it.toLong() }
        rangeList.add(Range(startOrigin, startDestination, length))
    }

    return rangeList
}
