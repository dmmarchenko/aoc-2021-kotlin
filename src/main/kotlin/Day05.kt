import java.lang.Integer.max
import kotlin.math.min

object Day05 {

    private val INPUT_PATTERN = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

    fun part1(input: List<String>): Int {
        return readInput(input)
            .filter { it.isParallelToAxe() }
            .flatMap {
                it.getAllPoints()
            }
            .groupBy { it }
            .map { it.value.size }
            .count { it >= 2 }
    }

    fun part2(input: List<String>): Int {
        return readInput(input)
            .flatMap {
                it.getAllPoints()
            }
            .groupBy { it }
            .map { it.value.size }
            .count { it >= 2 }
    }

    private fun readInput(input: List<String>): List<Line> {
        return input
            .map {
                val (x1, y1, x2, y2) = INPUT_PATTERN.find(it)!!.destructured
                Line(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt()))
            }
    }

    data class Point(val x: Int, val y: Int)

    class Line(val start: Point, val end: Point) {
        fun getAllPoints(): List<Point> {
            return if (start.x == end.x) {
                (min(start.y, end.y)..max(start.y, end.y)).map {
                    Point(start.x, it)
                }
            } else if (start.y == end.y) {
                (min(start.x, end.x)..max(start.x, end.x)).map {
                    Point(it, start.y)
                }
            } else {
                val deltaX = if (end.x > start.x) 1 else -1
                val deltaY = if (end.y > start.y) 1 else -1
                var p = start
                val points = mutableListOf<Point>()
                points.add(p)

                while (p != end) {
                    p = Point(p.x + deltaX, p.y + deltaY)
                    points.add(p)
                }

                points
            }
        }

        fun isParallelToAxe(): Boolean {
            return start.x == end.x || start.y == end.y
        }
    }
}