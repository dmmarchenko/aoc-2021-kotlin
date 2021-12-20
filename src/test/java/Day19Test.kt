import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day19Test {

    @Test
    fun permutations() {
        val p = Day19.Point(1, 2, 3)
        for (i in (0..23)) {
            val point = Day19.permutation(p, i)
            println(point)
        }
    }

    @Test
    fun part1Simple() {
        val actualResult = Day19.part1(readInput("day19Simple"))
        assertEquals(79, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day19.part1(readInput("day19Real"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day19.part2(readInput("day19Simple"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day19.part2(readInput("day19Real"))
        assertEquals(0, actualResult)
    }
}