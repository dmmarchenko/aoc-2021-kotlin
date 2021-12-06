import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Test {

    @Test
    fun part1Simple() {
        val actualResult = Day06.part1(readInput("day6Simple"))
        assertEquals(5934, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day06.part1(readInput("day6Real"))
        assertEquals(394994, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day06.part2(readInput("day6Simple"))
        assertEquals(26984457539, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day06.part2(readInput("day6Real"))
        assertEquals(1765974267455, actualResult)
    }
}