import kotlin.math.min

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
        var pos1 = initPositions(startingPosition(input[0]), 1)
        val totals1 = mutableMapOf<Int, Long>()

        var pos2 = initPositions(startingPosition(input[1]), 1)
        val totals2 = mutableMapOf<Int, Long>()

        var moves = 0
        while (true) {
            moves++

            pos1 = makeMove(pos1, totals1)
            if (totals1.won()) return totals1.wonUniverses()

            pos2 = makeMove(pos2, totals2)
            if (totals2.won()) return totals2.wonUniverses()

            println("Move")
        }
    }

    private fun Map<Int, Long>.won(): Boolean = this.keys.any { it >= 21 }

    private fun Map<Int, Long>.wonUniverses(): Long = this.filterKeys { it >= 21 }
        .map { it.value }
        .sum()

    private fun initPositions(startingPos: Int, multiplier: Long): Map<Int, Long> {
        return increments.mapKeys { movePosition(startingPos, it.key) }
            .mapValues { it.value.toLong() * multiplier }
            .toMutableMap()
    }

    private fun makeMove(positions: Map<Int, Long>, totals: MutableMap<Int, Long>): Map<Int, Long> {
        val newPositions = positions.flatMap { (value, positionCount) ->
            initPositions(value, positionCount).entries
        }
            .groupBy({ it.key }) { it.value }
            .mapValues { it.value.sum() }

        val newTotals = positions.flatMap { (value, positionCount) ->
            initPositions(value, positionCount).mapKeys { value + it.key }.entries
        }.groupBy({ it.key }) { it.value }
            .mapValues { it.value.sum() }

        newTotals.forEach { (v, count) ->
            if (v in totals) {
                totals[v] = totals[v]!! + count
            } else {
                totals[v] = count
            }
        }

        return newPositions
    }

    private fun startingPosition(str: String): Int = str.substring(28).toInt()

    private fun movePosition(pos: Int, move: Int): Int {
        var newPos = (pos + move) % 10
        if (newPos == 0) {
            newPos = 10
        }
        return newPos
    }

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