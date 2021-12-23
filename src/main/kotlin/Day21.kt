import kotlin.math.max
import kotlin.math.min

typealias Positions = Map<Day21.Stats, Long>

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
        val state = GameState(0, 0, initPositions(input))

        while (state.positions.isNotEmpty()) {
            makeMove(state)
        }
        return max(state.firstWon, state.secondWon)
    }

    private fun initPositions(input: List<String>): Positions {
        return nextPositions(
            startingPosition(input.first()),
            startingPosition(input.last()), 1
        )
            .mapKeys { Stats(it.key.first, it.key.first, it.key.second, it.key.second) }
    }

    private fun nextPositions(pos1: Int, pos2: Int, multiplier: Long): Map<Pair<Int, Int>, Long> {
        return increments.mapKeys { movePosition(pos1, it.key) to movePosition(pos2, it.key) }
            .mapValues { it.value.toLong() * multiplier }
    }

    private fun makeMove(state: GameState) {
        val positions = state.positions
            .flatMap { (position, count) ->
                nextPositions(position.pos1, position.pos2, count)
                    .mapKeys {
                        Stats(
                            it.key.first, position.total1 + it.key.first,
                            it.key.second, position.total2 + it.key.second
                        )
                    }.entries
            }
            .groupBy({ it.key }) { it.value }
            .mapValues { it.value.sum() }

        positions.filter { it.key.gameFinished() }
            .forEach { (stats, count) ->
                if (stats.firstWon()) {
                    state.firstWon += count
                } else {
                    state.secondWon += count
                }
            }

        state.positions = positions.filter { !it.key.gameFinished() }
    }

    private fun startingPosition(str: String): Int = str.substring(28).toInt()

    private fun movePosition(pos: Int, move: Int): Int {
        var newPos = (pos + move) % 10
        if (newPos == 0) {
            newPos = 10
        }
        return newPos
    }

    data class GameState(var firstWon: Long, var secondWon: Long, var positions: Positions)

    data class Stats(val pos1: Int, val total1: Int, val pos2: Int, val total2: Int) {
        fun gameFinished(): Boolean = total1 >= 21 || total2 >= 21

        fun firstWon(): Boolean = total1 >= 21
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