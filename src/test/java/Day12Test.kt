import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day12Test {

    @ParameterizedTest
    @CsvSource(
        "day12Simple1,10"
        //"day12Simple2,19",
        //"day12Simple3,226"
    )
    fun part1Simple1(fileName: String, expectedAnswer: Int) {
        val actualResult = Day12.part1(readInput(fileName))
        assertEquals(expectedAnswer, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day12.part1(readInput("day12Real"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day12.part2(readInput("day12Simple"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day12.part2(readInput("day12Real"))
        assertEquals(0, actualResult)
    }
}