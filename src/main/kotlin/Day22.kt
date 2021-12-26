object Day22 {

    fun part1(input: List<String>): Long {
        val cuboids = parseInput(input)
            .filter { it.inBounds() }
        return solve(cuboids)
    }

    fun part2(input: List<String>): Long {
        val cuboids = parseInput(input)
        return solve(cuboids)
    }

    private fun parseInput(input: List<String>): List<Cuboid> {
        return input.map { line ->
            val (commandRaw, coordsRaw) = line.split(" ")
            val sign = if (commandRaw == "on") 1 else -1
            val (x, y, z) = coordsRaw.split(",")
                .map { it.substring(2) }
                .map { coord ->
                    coord.split("..")
                        .map { it.toInt() }
                        .toIntRange()
                }
            Cuboid(x, y, z, sign)
        }
    }

    private fun List<Int>.toIntRange(): IntRange = this[0]..this[1]

    private data class Point(val x: Int, val y: Int, val z: Int)

    private fun solve(cuboids: List<Cuboid>): Long {
        return cuboids.fold(listOf<Cuboid>()) { acc, cuboid ->
            acc + acc.mapNotNull { it.intersect(cuboid) }
                .let {
                    if (cuboid.sign == 1) it + cuboid else it
                }
        }.sumOf { it.pointsNumber() }
    }

    private data class Cuboid(val x: IntRange, val y: IntRange, val z: IntRange, val sign: Int) {

        fun isIntersect(other: Cuboid): Boolean {
            return x.isIntersect(other.x) &&
                    y.isIntersect(other.y) &&
                    z.isIntersect(other.z)
        }

        fun intersect(other: Cuboid): Cuboid? {
            if (isIntersect(other)) {
                val xCord = this.x.intersect(other.x)
                val yCord = this.y.intersect(other.y)
                val zCord = this.z.intersect(other.z)
                return Cuboid(xCord, yCord, zCord, this.sign * -1)
            }
            return null
        }

        fun inBounds(): Boolean {
            return listOf(x.first, y.first, z.first).all { it >= -50 } &&
                    listOf(x.last, y.last, z.last).all { it <= 50 }
        }

        fun pointsNumber(): Long {
            return sign * x.length().toLong() * y.length().toLong() * z.length().toLong()
        }

        override fun toString(): String {
            return listOf(x.toString(), y.toString(), z.toString()).joinToString()
        }
    }

    private fun IntRange.isIntersect(other: IntRange): Boolean {
        return first <= other.last && this.last >= other.first

    }

    private fun IntRange.intersect(other: IntRange): IntRange {
        return maxOf(this.first, other.first)..minOf(this.last, other.last)
    }

    private fun IntRange.length(): Int {
        return this.last - this.first + 1
    }
}