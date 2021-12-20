object Day19 {

    private val zOrientation = listOf<(Point) -> Point>(
        { it },
        { Point(it.z, it.y, it.x) },
        { Point(it.x, it.z, it.y) },
    )
    private val plusMinus = listOf<(Point) -> Point>(
        { it },
        { Point(it.x, it.y, -it.z) },
        { Point(it.x, -it.y, it.z) },
        { Point(it.x, -it.y, -it.z) },
        { Point(-it.x, it.y, it.z) },
        { Point(-it.x, it.y, -it.z) },
        { Point(-it.x, -it.y, it.z) },
        { Point(-it.x, -it.y, -it.z) },
    )

    fun part1(input: List<String>): Int {
        val scanners = parseInput(input)
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    private fun parseInput(input: List<String>): List<List<Point>> {
        val result = mutableListOf<MutableList<Point>>()
        var currentList = mutableListOf<Point>()

        for (line in input) {
            if (line.contains(",")) {
                val (x, y, z) = line.split(",").map { it.toInt() }
                currentList += Point(x, y, z)
            }
            if (line.isEmpty()) {
                result += currentList
                currentList = mutableListOf()
            }
        }
        return result
    }

    fun permutation(p: Point, i: Int): Point {
        val zDirectionIndex = i / 8
        val plusMinusIndex = i % 8

        return plusMinus[plusMinusIndex](zOrientation[zDirectionIndex](p))
    }

    data class Point(val x: Int, val y: Int, val z: Int)
}