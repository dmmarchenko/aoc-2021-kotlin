import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day00Test {

    @Test
    fun part1Simple() {
        val actualResult = Day00.part1(readInput("day0Simple"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day00.part1(readInput("day0Real"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day00.part2(readInput("day0Simple"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day00.part2(readInput("day0Real"))
        assertEquals(0, actualResult)
    }
}