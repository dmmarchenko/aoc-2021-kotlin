import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun part1Simple() {
        val actualResult = Day10.part1(readInput("day10Simple"))
        assertEquals(26397, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day10.part1(readInput("day10Real"))
        assertEquals(411471, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day10.part2(readInput("day10Simple"))
        assertEquals(288957, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day10.part2(readInput("day10Real"))
        assertEquals(3122628974, actualResult)
    }
}