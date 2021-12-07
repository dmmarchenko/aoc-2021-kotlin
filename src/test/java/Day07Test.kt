import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day07Test {

    @Test
    fun part1Simple() {
        val actualResult = Day07.part1(readInput("day7Simple"))
        assertEquals(37, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day07.part1(readInput("day7Real"))
        assertEquals(355592, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day07.part2(readInput("day7Simple"))
        assertEquals(168, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day07.part2(readInput("day7Real"))
        assertEquals(101618069, actualResult)
    }
}