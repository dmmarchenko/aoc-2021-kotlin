import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {

    @Test
    internal fun part1Simple() {
        val actualResult = Day03.part1(readInput("day3Simple"))
        assertEquals(198, actualResult)
    }

    @Test
    internal fun part1Real() {
        val actualResult = Day03.part1(readInput("day3Real"))
        assertEquals(2003336, actualResult)
    }

    @Test
    internal fun part2Simple() {
        val actualResult = Day03.part2(readInput("day3Simple"))
        assertEquals(230, actualResult)
    }

    @Test
    internal fun part2Real() {
        val actualResult = Day03.part2(readInput("day3Real"))
        assertEquals(1877139, actualResult)
    }
}