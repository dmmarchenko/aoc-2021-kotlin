import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    internal fun part1Simple() {
        val actualResult = Day02.part1(readInput("day2Simple"))
        assertEquals(150, actualResult)
    }

    @Test
    internal fun part1Real() {
        val actualResult = Day02.part1(readInput("day2Real"))
        assertEquals(2215080, actualResult)
    }

    @Test
    internal fun part2Simple() {
        val actualResult = Day02.part2(readInput("day2Simple"))
        assertEquals(900, actualResult)
    }

    @Test
    internal fun part2Real() {
        val actualResult = Day02.part2(readInput("day2Real"))
        assertEquals(1864715580, actualResult)
    }
}