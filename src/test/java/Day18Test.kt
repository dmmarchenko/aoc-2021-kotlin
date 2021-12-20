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

    fun explodeArgs(): List<Arguments> {
        return listOf(
            Arguments.of("[[[[[9,8],1],2],3],4]", "[[[[0,9],2],3],4]"),
            Arguments.of("[7,[6,[5,[4,[3,2]]]]]", "[7,[6,[5,[7,0]]]]"),
            Arguments.of("[[6,[5,[4,[3,2]]]],1]", "[[6,[5,[7,0]]],3]"),
            Arguments.of("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]", "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"),
            Arguments.of("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", "[[3,[2,[8,0]]],[9,[5,[7,0]]]]"),
        )
    }

    @ParameterizedTest
    @MethodSource("explodeArgs")
    fun explode(input: String, expectedExploded: String) {
        val number = Day18.parseNumber(input)
        val explode = number.explode()
        assertEquals(true, explode)
        assertEquals(expectedExploded, number.asString())
    }

    @Test
    fun noExplode() {
        val input = "[[3,[2,[8,0]]],[9,[5,[7,0]]]]"
        val number = Day18.parseNumber(input)
        val explode = number.explode()
        assertEquals(false, explode)
        assertEquals(input, number.asString())
    }

    @Test
    fun split() {
        val input = "[[[[0,7],4],[15,[0,13]]],[1,1]]"
        val number = Day18.parseNumber(input)
        val split = number.split()
        assertEquals(true, split)
        assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", number.asString())
    }

    @Test
    fun plus() {
        val a = Day18.parseNumber("[[[[4,3],4],4],[7,[[8,4],9]]]")
        val b = Day18.parseNumber("[1,1]")
        val sum = a.plus(b)
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", sum.asString())
    }

    @Test
    fun part1Simple() {
        val actualResult = Day18.part1(readInput("day18Simple"))
        assertEquals(4140, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day18.part1(readInput("day18Real"))
        assertEquals(4289, actualResult)
    }

    @Test
    fun part2Simple() {
        val actualResult = Day18.part2(readInput("day18Simple"))
        assertEquals(3993, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day18.part2(readInput("day18Real"))
        assertEquals(4807, actualResult)
    }
}