import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day18Test {

    fun magnitudeArgs(): List<Arguments> {
        return listOf(
            Arguments.of("[[1,2],[[3,4],5]]", 143),
            Arguments.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", 1384),
            Arguments.of("[[[[1,1],[2,2]],[3,3]],[4,4]]", 445),
            Arguments.of("[[[[3,0],[5,3]],[4,4]],[5,5]]", 791),
            Arguments.of("[[[[5,0],[7,4]],[5,5]],[6,6]]", 1137),
            Arguments.of("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", 3488)
        )
    }

    @ParameterizedTest
    @MethodSource("magnitudeArgs")
    fun magnitude(numberStr: String, expectedMagnitude: Long) {
        val number = Day18.parseNumber(numberStr)
        assertEquals(expectedMagnitude, number.magnitude())
    }

    @Test
    fun traverse() {
        val number = Day18.parseNumber("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
        number.traverse()
    }

    @Test
    fun part1Simple() {
        val actualResult = Day18.part1(readInput("day18Simple"))
        assertEquals(4140, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day18.part1(readInput("day18Real"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day18.part2(readInput("day18Simple"))
        assertEquals(0, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day18.part2(readInput("day18Real"))
        assertEquals(0, actualResult)
    }
}