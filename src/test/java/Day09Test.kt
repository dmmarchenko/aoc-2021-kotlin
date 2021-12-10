import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day09Test {

    @Test
    fun part1Simple() {
        val actualResult = Day09.part1(readInput("day9Simple"))
        assertEquals(15, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day09.part1(readInput("day9Real"))
        assertEquals(462, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day09.part2(readInput("day9Simple"))
        assertEquals(1134, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day09.part2(readInput("day9Real"))
        assertEquals(1397760, actualResult)
    }
}