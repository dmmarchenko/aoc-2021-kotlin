import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun part1Simple() {
        val actualResult = Day11.part1(readInput("day11Simple"))
        assertEquals(1656, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day11.part1(readInput("day11Real"))
        assertEquals(1634, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day11.part2(readInput("day11Simple"))
        assertEquals(195, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day11.part2(readInput("day11Real"))
        assertEquals(210, actualResult)
    }
}