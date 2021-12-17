import kotlin.math.max

object Day17 {

    fun part1(input: List<String>): Int {
        return modulateThrows(input).first
    }

    fun part2(input: List<String>): Int {
        return modulateThrows(input).second
    }

    private fun modulateThrows(input: List<String>): Pair<Int, Int> {
        val probe = readInput(input)
        var maxY = 0
        var count = 0

        for (vx in (0..probe.x.last)) {
            if (canTargetX(vx, probe)) {
                for (vy in (-200..1000)) {
                    val maxYResult = maxY(vx, vy, probe)
                    if (maxYResult.first) {
                        count++
                        maxY = max(maxY, maxYResult.second)
                    }
                }
            }
        }

        return maxY to count
    }

    fun maxY(vx: Int, vy: Int, probe: Probe): Pair<Boolean, Int> {
        var x = 0
        var y = 0
        var t = 1
        var maxY = 0

        while (x <= probe.x.last && y >= probe.y.first) {
            x = x(vx, t)
            y = y(vy, t)
            maxY = max(maxY, y)
            if (x in probe.x && y in probe.y) {
                return true to maxY
            }
            t++
        }
        return false to 0
    }

    private fun canTargetX(vx: Int, probe: Probe): Boolean {
        for (t in 0..vx) {
            val x = vx * t - (t - 1) * t / 2
            if (x in probe.x) {
                return true
            }
        }
        return false
    }

    private fun y(vy: Int, t: Int): Int {
        return t * vy - (t - 1) * t / 2
    }

    private fun x(vx: Int, t: Int): Int {
        return if (t <= vx) {
            t * vx - (t - 1) * t / 2
        } else x(vx, vx)
    }

    private fun readInput(input: List<String>): Probe {
        val (x, y) = input.first()
            .substring(13)
            .split(", ")
            .map { it.substring(2) }
        val (x1, x2) = x.split("..").map { it.toInt() }
        val (y1, y2) = y.split("..").map { it.toInt() }
        return Probe(x1..x2, y1..y2)
    }

    data class Probe(val x: IntRange, val y: IntProgression)
}