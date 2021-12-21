import sample.Day19Sample

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
        return Day19Sample(input.joinToString(separator = "\n")).solvePart1()
    }

    fun myPart1(input: List<String>): Int {
        val scanners = parseInput(input)
        val scanner0 = scanners[0]
        val scanner1 = scanners[1]

        val diffs0 = allDiffs(scanner0)

        (0..23).forEach { i ->
            val rotatedScanner1 = scanner1.rotate(i)
            val diffs1 = allDiffs(rotatedScanner1)
            val inters = diffs0.intersect(diffs1)
            if (inters.size > 66) {
                println(inters)
            }
        }
        return 0
    }

    private fun allDiffs(points: List<Point>): Set<Point> {
        val diffs = mutableSetOf<Point>()

        points.indices.forEach { i ->
            points.indices.filter { it != i }
                .forEach { j ->
                    diffs += points[j] - points[i]
                }
        }
        return diffs
    }

    fun part2(input: List<String>): Int {
        return Day19Sample(input.joinToString(separator = "\n")).solvePart2()
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

    fun List<Point>.rotate(rotationIndex: Int): List<Point> = this.map { rotate(it, rotationIndex) }

    fun rotate(p: Point, i: Int): Point {
        val zDirectionIndex = i / 8
        val plusMinusIndex = i % 8

        return plusMinus[plusMinusIndex](zOrientation[zDirectionIndex](p))
    }

    data class Point(val x: Int, val y: Int, val z: Int) {
        operator fun minus(b: Point): Point {
            return Point(this.x - b.x, this.y - b.y, this.z - b.z)
        }
    }
}