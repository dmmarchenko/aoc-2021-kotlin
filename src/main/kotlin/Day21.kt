import kotlin.math.min

object Day21 {

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

    fun part2(input: List<String>): Int {
        return 0
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