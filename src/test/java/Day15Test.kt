import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun part1Simple() {
        val actualResult = Day15.part1(readInput("day15Simple"))
        assertEquals(40, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day15.part1(readInput("day15Real"))
        assertEquals(741, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day15.part2(readInput("day15Simple"))
        assertEquals(315, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day15.part2(readInput("day15Real"))
        assertEquals(0, actualResult)
    }
}