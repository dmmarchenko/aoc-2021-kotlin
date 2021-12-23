import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day21Test {

    @Test
    fun part1Simple() {
        val actualResult = Day21.part1(readInput("day21Simple"))
        assertEquals(739785, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day21.part1(readInput("day21Real"))
        assertEquals(918081, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day21.part2(readInput("day21Simple"))
        assertEquals(444356092776315, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day21.part2(readInput("day21Real"))
        assertEquals(0, actualResult)
    }
}