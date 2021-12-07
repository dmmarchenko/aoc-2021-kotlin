import org.nield.kotlinstatistics.median
import kotlin.math.abs
import kotlin.math.roundToInt

object Day07 {

    fun part1(input: List<String>): Int {
        val coords = parseInput(input)
        return alignCost(coords.median().toInt(), coords)
    }

    fun part2(input: List<String>): Int {
        val coords = parseInput(input)
        val avg = coords.average().roundToInt()
        val minCost = (avg - 1..avg + 1)
            .map { alignCost2(it, coords) }
            .minOf { it }
        return minCost
    }

    private fun alignCost2(alignTo: Int, coords: List<Int>): Int {
        return coords.sumOf { sumN(abs(it - alignTo)) }
    }

    private fun sumN(n: Int): Int = n * (n + 1) / 2

    private fun alignCost(alignTo: Int, coords: List<Int>): Int {
        return coords.sumOf { abs(it - alignTo) }
    }

    private fun parseInput(input: List<String>): List<Int> {
        return input.first()
            .split(",")
            .map { it.toInt() }
    }
}