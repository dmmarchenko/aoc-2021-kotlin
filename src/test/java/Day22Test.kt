import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day22Test {

    @Test
    fun part1Lite() {
        val actualResult = Day22.part1(readInput("day22Lite"))
        assertEquals(39, actualResult)
    }

    @Test
    fun part1Simple() {
        val actualResult = Day22.part1(readInput("day22Simple"))
        assertEquals(590784, actualResult)
    }

    @Test
    fun part1Real() {
        val actualResult = Day22.part1(readInput("day22Real"))
        assertEquals(537042, actualResult)
    }

    @Test
    fun part1Harder() {
        val actualResult = Day22.part1(readInput("day22Harder"))
        assertEquals(474140, actualResult)
    }

    @Test
    fun part2AddTwo() {
        val actualResult = Day22.part2(listOf("on x=10..12,y=10..12,z=10..12", "on x=11..13,y=11..13,z=11..13"))
        assertEquals(46, actualResult)
    }

    @Test
    fun part2MinusOne() {
        val actualResult = Day22.part2(listOf("on x=10..12,y=10..12,z=10..12", "off x=11..13,y=11..13,z=11..13"))
        assertEquals(19, actualResult)
    }

    @Test
    fun part2MinusOne2() {
        val actualResult = Day22.part2(listOf("on x=11..13,y=11..13,z=11..13", "off x=10..12,y=10..12,z=10..12"))
        assertEquals(19, actualResult)
    }

    @Test
    fun part2MinusOne3() {
        val actualResult = Day22.part2(
            listOf(
                "on x=10..12,y=10..12,z=10..12",
                "on x=11..13,y=11..13,z=11..13",
                "off x=9..11,y=9..11,z=9..11"
            )
        )
        assertEquals(38, actualResult)
    }

    @Test
    fun part2Lite() {
        val actualResult = Day22.part2(readInput("day22Lite"))
        assertEquals(39, actualResult)
    }

    @Test
    fun part2Harder() {
        val actualResult = Day22.part2(readInput("day22Harder"))
        assertEquals(2_758_514_936_282_235, actualResult)
    }

    @Test
    fun part2Real() {
        val actualResult = Day22.part2(readInput("day22Real"))
        assertEquals(1304385553084863, actualResult)
    }
}