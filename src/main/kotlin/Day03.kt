import kotlin.math.pow

object Day03 {

    fun part1(input: List<String>): Int {
        val gamaBinary = buildIndexMap(input)
        val gama = gamaBinary.toDecimal()
        val epsilon = gamaBinary.invert().toDecimal()
        return gama * epsilon
    }

    fun part2(input: List<String>): Int {
        val oxygenRating = findRating(input, false)
        val co2Rating = findRating(input, true)
        return oxygenRating * co2Rating
    }

    private fun findRating(input: List<String>, leastCommon: Boolean): Int {
        var valRatings = input
        var lookupIndex = 0

        do {
            valRatings = filterRatings(valRatings, leastCommon, lookupIndex++)
        } while (valRatings.size > 1)

        return valRatings.first()
            .toDecimal()
    }

    private fun filterRatings(input: List<String>, leastCommon: Boolean, lookupIndex: Int): List<String> {
        val countOfOnes = input.map {
            it[lookupIndex].digitToInt()
        }.count { it == 1 }
        var mostCommon = moreThanAHalf(input.size, countOfOnes)
        if (leastCommon) {
            mostCommon = !mostCommon
        }
        val targetBit = mostCommon.toBit()

        return input.filter {
            it[lookupIndex].digitToInt() == targetBit
        }
    }

    private fun moreThanAHalf(total: Int, count: Int): Boolean {
        val half = total / 2
        return if (total % 2 == 0) {
            count >= half
        } else {
            count > half
        }
    }

    private fun Boolean.toBit(): Int {
        if (this) {
            return 1
        }
        return 0
    }

    private fun buildIndexMap(input: List<String>): List<Int> {
        val threshold = input.size / 2
        return input.flatMap {
            it.mapIndexed { index, c ->
                index to c
            }
        }
            .groupBy { indexToChar -> indexToChar.first }
            .mapValues { listOfEntries ->
                listOfEntries.value
                    .map { it.second }
                    .count { it == '1' }
            }
            .mapValues { indexToNumberOfOnes ->
                indexToNumberOfOnes.value > threshold
            }
            .toSortedMap()
            .values
            .map { it.toBit() }
    }

    private fun List<Int>.toDecimal(): Int {
        return this.reversed().asSequence()
            .withIndex()
            .filter { it.value == 1 }
            .map { it.index }
            .map { 2.0.pow(it.toDouble()) }
            .sum()
            .toInt()
    }

    private fun String.toDecimal(): Int = this
        .asIterable()
        .map { it.digitToInt() }
        .toDecimal()

    private fun List<Int>.invert(): List<Int> = this.map { 1 - it }
}