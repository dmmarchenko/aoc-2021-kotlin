import kotlin.math.abs

object Day19 {

    fun part1(input: List<String>): Int {
        val scanners = parseInput(input)
        return assembleMap(scanners).beacons.size
    }

    fun part2(input: List<String>): Int {
        val scanners = parseInput(input)
        val assembleMap = assembleMap(scanners)
        return assembleMap.scannersPositions.let { positions ->
            positions.flatMapIndexed { index, first -> positions.drop(index + 1).map { second -> first to second } }
                .maxOf { (first, second) -> first distanceTo second }
        }
    }

    private fun assembleMap(scanners: List<Scanner>): AssembledMap {
        val foundBeacons = scanners.first().beacons.toMutableSet()
        val foundScannersPositions = mutableSetOf(Point(0, 0, 0))

        val remaining = ArrayDeque<Scanner>().apply { addAll(scanners.drop(1)) }
        while (remaining.isNotEmpty()) {
            val candidate = remaining.removeFirst()
            when (val transformedCandidate = Scanner(foundBeacons).getTransformedIfOverlap(candidate)) {
                null -> remaining.add(candidate)
                else -> {
                    foundBeacons.addAll(transformedCandidate.beacons)
                    foundScannersPositions.add(transformedCandidate.position)
                }
            }
        }

        return AssembledMap(foundBeacons, foundScannersPositions)
    }

    private fun parseInput(input: List<String>): List<Scanner> {
        val scanners = mutableListOf<Scanner>()
        var beacons = mutableSetOf<Point>()

        for (line in input) {
            if (line.contains(",")) {
                val (x, y, z) = line.split(",").map { it.toInt() }
                beacons += Point(x, y, z)
            }
            if (line.isEmpty()) {
                scanners += Scanner(beacons)
                beacons = mutableSetOf()
            }
        }
        return scanners
    }

    data class Scanner(val beacons: Set<Point>) {

        fun allRotations() = beacons.map { it.allRotations() }.transpose().map { Scanner(it) }

        fun getTransformedIfOverlap(otherScanner: Scanner): TransformedScanner? {
            return otherScanner.allRotations().firstNotNullOfOrNull { otherReoriented ->
                beacons.firstNotNullOfOrNull { first ->
                    otherReoriented.beacons.firstNotNullOfOrNull { second ->
                        val otherPosition = first - second
                        val otherTransformed = otherReoriented.beacons.map { otherPosition + it }.toSet()
                        when ((otherTransformed intersect beacons).size >= 12) {
                            true -> TransformedScanner(otherTransformed, otherPosition)
                            false -> null
                        }
                    }
                }
            }
        }

        private fun List<Set<Point>>.transpose(): List<Set<Point>> {
            return when (all { it.isNotEmpty() }) {
                true -> listOf(map { it.first() }.toSet()) + map { it.drop(1).toSet() }.transpose()
                false -> emptyList()
            }
        }
    }

    data class AssembledMap(val beacons: Set<Point>, val scannersPositions: Set<Point>)

    data class TransformedScanner(val beacons: Set<Point>, val position: Point)

    data class Point(val x: Int, val y: Int, val z: Int) {
        operator fun minus(b: Point): Point {
            return Point(this.x - b.x, this.y - b.y, this.z - b.z)
        }

        operator fun plus(b: Point): Point {
            return Point(this.x + b.x, this.y + b.y, this.z + b.z)
        }

        infix fun distanceTo(b: Point): Int {
            return abs(this.x - b.x) + abs(this.y - b.y) + abs(this.z - b.z)
        }

        fun allRotations(): Set<Point> {
            return setOf(
                Point(x, y, z), Point(x, -z, y), Point(x, -y, -z), Point(x, z, -y), Point(-x, -y, z),
                Point(-x, -z, -y), Point(-x, y, -z), Point(-x, z, y), Point(-z, x, -y), Point(y, x, -z),
                Point(z, x, y), Point(-y, x, z), Point(z, -x, -y), Point(y, -x, z), Point(-z, -x, y),
                Point(-y, -x, -z), Point(-y, -z, x), Point(z, -y, x), Point(y, z, x), Point(-z, y, x),
                Point(z, y, -x), Point(-y, z, -x), Point(-z, -y, -x), Point(y, -z, -x),
            )
        }
    }
}