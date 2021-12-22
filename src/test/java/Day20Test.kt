import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun part1Simple() {
        val actualResult = Day20.part1(readInput("day20Simple"))
        assertEquals(35, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day20.part1(readInput("day20Real"))
        assertEquals(5275, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day20.part2(readInput("day20Simple"))
        assertEquals(3351, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day20.part2(readInput("day20Real"))
        assertEquals(16482, actualResult)
    }
}