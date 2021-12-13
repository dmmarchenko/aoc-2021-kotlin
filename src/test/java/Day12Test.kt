import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day12Test {

    @ParameterizedTest
    @CsvSource(
        "day12Simple1,10",
        "day12Simple2,19",
        "day12Simple3,226"
    )
    fun part1Simple2(fileName: String, expectedAnswer: Int) {
        val actualResult = Day12.part1(readInput(fileName))
        assertEquals(expectedAnswer, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day12.part1(readInput("day12Real"))
        assertEquals(4885, actualResult)
    }

    @ParameterizedTest
    @CsvSource(
        "day12Simple1,36",
        "day12Simple2,103",
        "day12Simple3,3509"
    )
    fun part2Simple(fileName: String, expectedAnswer: Int) {
        val actualResult = Day12.part2(readInput(fileName))
        assertEquals(expectedAnswer, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day12.part2(readInput("day12Real"))
        assertEquals(0, actualResult)
    }
}