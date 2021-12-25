object Day22 {

    fun part1(input: List<String>): Int {
        val commands = parseInput(input)
        val lightPoints = mutableSetOf<Point>()

        commands
            .filter { it.cuboid.inBounds() }
            .forEach {
                val points = it.cuboid.points()
                if (it.on) {
                    lightPoints += points
                } else {
                    lightPoints -= points
                }
            }

        return lightPoints.size
    }

    fun part2(input: List<String>): Long {
        val commands = parseInput(input)
        val cuboids = Cuboids()

        commands.forEach { (on, cuboid) ->
            if (on) {
                cuboids.plus(cuboid)
            } else {
                cuboids.minus(cuboid)
            }
        }

        for (c in cuboids.cuboids) {
            for (cc in cuboids.cuboids) {
                if (c != cc && c.isIntersect(cc)) {
                    println("$c <> $cc")
                }
            }
        }

        return cuboids.pointsNumber()
    }

    private fun parseInput(input: List<String>): List<Command> {
        return input.map { line ->
            val (commandRaw, coordsRaw) = line.split(" ")
            val on = commandRaw == "on"
            val (x, y, z) = coordsRaw.split(",")
                .map { it.substring(2) }
                .map { coord ->
                    coord.split("..")
                        .map { it.toInt() }
                        .toIntRange()
                }
            Command(on, Cuboid(x, y, z))
        }
    }

    private fun List<Int>.toIntRange(): IntRange = this[0]..this[1]

    private data class Point(val x: Int, val y: Int, val z: Int)

    private data class Command(val on: Boolean, val cuboid: Cuboid)

    private class Cuboids {
        val cuboids = mutableListOf<Cuboid>()

        fun plus(cuboid: Cuboid) {
            val intersects = cuboids.filter { it.isIntersect(cuboid) }
            if (intersects.isEmpty()) {
                cuboids += cuboid
                return
            }

            val firstPlus = intersects.first().plus(cuboid)
            if (intersects.size == 1) {
                cuboids += firstPlus
                return
            }

            firstPlus.forEach {
                plus(it)
            }
        }

        fun minus(cuboid: Cuboid) {
            val intersects = cuboids.filter { it.isIntersect(cuboid) }
            if (intersects.isNotEmpty()) {
                intersects.forEach { intersected ->
                    cuboids -= intersected
                    cuboids += intersected.minus(cuboid)
                }
            }
        }

        fun pointsNumber(): Long {
            return cuboids.sumOf { it.pointsNumber() }
        }

        fun points(): Set<Point> {
            return cuboids.flatMap { it.points() }.toSet()
        }
    }

    private data class Cuboid(val x: IntRange, val y: IntRange, val z: IntRange) {

        fun isIntersect(other: Cuboid): Boolean {
            return x.isIntersect(other.x) &&
                    y.isIntersect(other.y) &&
                    y.isIntersect(other.y)
        }

        fun plus(other: Cuboid): List<Cuboid> {
            return join(this, other)
        }

        fun minus(other: Cuboid): List<Cuboid> {
            return join(other, this)
        }

        fun join(first: Cuboid, second: Cuboid): List<Cuboid> {
            val (_, intX, onlyX) = first.x.intersect(second.x)
            val (_, intY, onlyY) = first.y.intersect(second.y)
            val (_, intZ, onlyZ) = first.z.intersect(second.z)

            return listOf(
                Cuboid(onlyX, intY, intZ),
                Cuboid(onlyX, intY, onlyZ),
                Cuboid(intX, onlyY, intZ),
                Cuboid(intX, onlyY, onlyZ),
                Cuboid(onlyX, onlyY, intZ),
                Cuboid(onlyX, onlyY, onlyZ),
                Cuboid(intX, intY, onlyZ),
            )
                .filter { !it.isEmpty() }
        }

        fun points(): Set<Point> {
            return x.flatMap { xC ->
                y.flatMap { yC ->
                    z.map { zC ->
                        Point(xC, yC, zC)
                    }
                }
            }.toSet()
        }

        fun inBounds(): Boolean {
            return listOf(x.first, y.first, z.first).all { it >= -50 } &&
                    listOf(x.last, y.last, z.last).all { it <= 50 }
        }

        fun pointsNumber(): Long {
            return x.length() * y.length() * z.length()
        }

        private fun isEmpty(): Boolean {
            return x.isEmpty() || y.isEmpty() || z.isEmpty()
        }

        override fun toString(): String {
            return listOf(x.toString(), y.toString(), z.toString()).joinToString()
        }
    }

    private fun IntRange.isIntersect(other: IntRange): Boolean {
        return this.first <= other.first && this.last >= other.first
                || other.first <= this.first && other.last >= this.first
    }

    private fun IntRange.intersect(other: IntRange): List<IntRange> {
        val first = if (this.first <= other.first) this else other
        val second = if (this.first <= other.first) other else this

        return listOf(
            first.first until second.first,
            second.first..first.last,
            first.last + 1..second.last
        )
    }

    private fun IntRange.length(): Long {
        return this.last.toLong() - this.first + 1
    }
}