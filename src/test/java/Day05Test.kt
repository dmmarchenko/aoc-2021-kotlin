import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun part1Simple() {
        val actualResult = Day05.part1(readInput("day5Simple"))
        assertEquals(5, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day05.part1(readInput("day5Real"))
        assertEquals(6225, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day05.part2(readInput("day5Simple"))
        assertEquals(12, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day05.part2(readInput("day5Real"))
        assertEquals(0, actualResult)
    }
}