import kotlin.math.min

object Day21 {

    private val increments = mapOf<Int, Long>(
        3 to 1,
        4 to 3,
        5 to 6,
        6 to 7,
        7 to 6,
        8 to 3,
        9 to 1
    )
    private val cache = mutableMapOf<GameState, WinCount>()

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
        val gameState = GameState(
            PlayerState(startingPosition(input.first()) - 1, 0),
            PlayerState(startingPosition(input.last()) - 1, 0)
        )
        val winCount = play(gameState)
        return winCount.maxScore()
    }

    private fun startingPosition(str: String): Int = str.substring(28).toInt()

    private fun movePosition(pos: Int, move: Int): Int {
        var newPos = (pos + move) % 10
        if (newPos == 0) {
            newPos = 10
        }
        return newPos
    }

    private fun play(gameState: GameState): WinCount {
        return when {
            gameState.p1State.total >= 21 -> WinCount(1, 0)
            gameState.p2State.total >= 21 -> WinCount(0, 1)
            else -> cache.getOrPut(gameState) {
                increments.map { (inc, incCount) -> play(gameState.next(inc)) * incCount }
                    .reduce { acc, count -> acc + count }
            }
        }
    }

    private data class GameState(var p1State: PlayerState, val p2State: PlayerState, val p1Turn: Boolean = true) {
        fun next(roll: Int): GameState {
            return GameState(
                if (p1Turn) p1State.next(roll) else p1State,
                if (!p1Turn) p2State.next(roll) else p2State,
                !p1Turn
            )
        }
    }

    private data class PlayerState(val pos: Int, val total: Int) {
        fun next(roll: Int): PlayerState {
            val newPos = (pos + roll) % 10
            return PlayerState(newPos, total + newPos + 1)
        }
    }

    private data class WinCount(val p1: Long, val p2: Long) {
        operator fun plus(other: WinCount) = WinCount(p1 + other.p1, p2 + other.p2)

        operator fun times(other: Long) = WinCount(p1 * other, p2 * other)

        fun maxScore() = maxOf(p1, p2)
    }

    private class Dice {
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