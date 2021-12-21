import Day19.Point
import Day19.transpose
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day19Test {

    @Test
    fun transpose() {
        val sets = listOf(
            setOf(Point(10, 20, 30), Point(40, 50, 60), Point(70, 80, 90)),
            setOf(Point(1, 2, 3), Point(4, 5, 6), Point(7, 8, 9))
        )
        val transposed = sets.transpose()
        val expected = listOf(
            setOf(Point(10, 20, 30), Point(1, 2, 3)),
            setOf(Point(40, 50, 60), Point(4, 5, 6)),
            setOf(Point(70, 80, 90), Point(7, 8, 9))
        )
        assertEquals(expected, transposed)
    }

    @Test
    fun part1Simple() {
        val actualResult = Day19.part1(readInput("day19Simple"))
        assertEquals(79, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day19.part1(readInput("day19Real"))
        assertEquals(442, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day19.part2(readInput("day19Simple"))
        assertEquals(3621, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day19.part2(readInput("day19Real"))
        assertEquals(11079, actualResult)
    }
}