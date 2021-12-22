import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day19Test {

    @Test
    fun part1Simple() {
        val actualResult = Day19.part1(readInput("day19Simple"))
        assertEquals(79, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day19.part1(readInput("day19Real"))
        assertEquals(442, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day19.part2(readInput("day19Simple"))
        assertEquals(3621, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day19.part2(readInput("day19Real"))
        assertEquals(11079, actualResult)
    }
}