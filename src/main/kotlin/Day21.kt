import kotlin.math.min

typealias Positions = Map<Pair<Int, Int>, Long>

object Day21 {

    private val increments = mapOf(
        3 to 1,
        4 to 3,
        5 to 6,
        6 to 7,
        7 to 6,
        8 to 3,
        9 to 1
    )

    fun part1(input: List<String>): Long {
        var pos1 = startingPosition(input[0])
        var pos2 = startingPosition(input[1])
        var total1 = 0
        var total2 = 0
        var rolls = 0L
        val dice = Dice()

        while (true) {
            val r1 = dice.roll()
            pos1 = movePosition(pos1, r1)
            total1 += pos1
            rolls += 3
            if (total1 >= 1000) break

            val r2 = dice.roll()
            pos2 = movePosition(pos2, r2)
            total2 += pos2
            rolls += 3
            if (total2 >= 1000) break
        }

        val losePoints = min(total1, total2)
        return losePoints.toLong() * rolls
    }

    fun part2(input: List<String>): Long {
        var pos1 = initPositions(input[0])
        var pos2 = initPositions(input[1])

        var moves = 0
        while (true) {
            moves++

            pos1 = makeMove(pos1)
            if (pos1.won()) return pos1.wonUniverses()
            pos2 = makeMove(pos2)
            if (pos2.won()) return pos2.wonUniverses()
            println("Move")
        }
    }

    private fun initPositions(str: String) = nextPositions(startingPosition(str), 1)
        .mapKeys { it.key to it.key }

    private fun Positions.won(): Boolean = this.keys.any { it.second >= 21 }

    private fun Positions.wonUniverses(): Long = this.filterKeys { it.second >= 21 }
        .map { it.value }
        .sum()

    private fun nextPositions(startingPos: Int, multiplier: Long): Map<Int, Long> {
        return increments.mapKeys { movePosition(startingPos, it.key) }
            .mapValues { it.value.toLong() * multiplier }
    }

    private fun makeMove(positions: Positions): Positions {
        return positions.flatMap { (positionAndTotal, count) ->
            nextPositions(positionAndTotal.first, count)
                .mapKeys { it.key to (positionAndTotal.second + it.key) }.entries
        }
            .groupBy({ it.key }) { it.value }
            .mapValues { it.value.sum() }
    }

    private fun startingPosition(str: String): Int = str.substring(28).toInt()

    private fun movePosition(pos: Int, move: Int): Int {
        var newPos = (pos + move) % 10
        if (newPos == 0) {
            newPos = 10
        }
        return newPos
    }

    data class Stats(val pos1: Int, val total1: Int, val pos2: Int, val total2: Int)

    class Dice {
        var c = 1

        fun roll(): Int {
            val r = when (c) {
                99 -> 200
                100 -> 103
                else -> 3 * c + 3
            }

            c += +3
            if (c > 100) {
                c -= 100
            }
            return r
        }

    }
}