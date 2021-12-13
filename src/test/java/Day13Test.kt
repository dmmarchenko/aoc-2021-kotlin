import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun part1Simple() {
        val actualResult = Day13.part1(readInput("day13Simple"))
        assertEquals(17, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day13.part1(readInput("day13Real"))
        assertEquals(814, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day13.part2(readInput("day13Simple"))
        assertEquals(16, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day13.part2(readInput("day13Real"))
        assertEquals(108, actualResult)
        //Code PZEHRAER
    }
}