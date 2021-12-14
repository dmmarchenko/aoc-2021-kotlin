import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun part1Simple() {
        val actualResult = Day14.part1(readInput("day14Simple"))
        assertEquals(1588, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day14.part1(readInput("day14Real"))
        assertEquals(4517, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day14.part2(readInput("day14Simple"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day14.part2(readInput("day14Real"))
        assertEquals(0, actualResult)
    }
}