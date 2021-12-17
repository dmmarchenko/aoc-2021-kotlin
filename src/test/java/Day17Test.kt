import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day17Test {

    @Test
    fun maxY1() {
        val maxY = Day17.maxY(7, 2, Day17.Probe(20..30, -10..-5)).second
        assertEquals(3, maxY)
    }

    @Test
    fun maxY2() {
        val maxY = Day17.maxY(6, 3, Day17.Probe(20..30, -10..-5)).second
        assertEquals(6, maxY)
    }

    @Test
    fun maxY3() {
        val maxY = Day17.maxY(6, 9, Day17.Probe(20..30, -10..-5)).second
        assertEquals(45, maxY)
    }

    @Test
    fun throw7Minus1() {
        val maxY = Day17.maxY(7, -1, Day17.Probe(20..30, -10..-5))
        assertEquals(true, maxY.first)
    }

    @Test
    fun part1Simple() {
        val actualResult = Day17.part1(readInput("day17Simple"))
        assertEquals(45, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day17.part1(readInput("day17Real"))
        assertEquals(15400, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day17.part2(readInput("day17Simple"))
        assertEquals(112, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day17.part2(readInput("day17Real"))
        assertEquals(5844, actualResult)
    }
}