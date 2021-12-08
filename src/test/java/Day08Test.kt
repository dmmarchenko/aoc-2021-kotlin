import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day08Test {

    @Test
    fun part1Simple() {
        val actualResult = Day08.part1(readInput("day8Simple"))
        assertEquals(26, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day08.part1(readInput("day8Real"))
        assertEquals(310, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day08.part2(readInput("day8Simple"))
        assertEquals(61229, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day08.part2(readInput("day8Real"))
        assertEquals(915941, actualResult)
    }
}