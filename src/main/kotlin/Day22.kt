object Day22 {

    fun part1(input: List<String>): Int {
        val commands = parseInput(input)
        val min = commands.map { it.cuboid }
            .minOf { it.min() }
        val max = commands.map { it.cuboid }
            .maxOf { it.max() }
        println("$min - $max")

        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
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

    private class Command(val on: Boolean, val cuboid: Cuboid)

    private class Cuboid(val x: IntRange, val y: IntRange, val z: IntRange) {
        fun min(): Int {
            return listOf(x, y, z)
                .map { it.first }
                .minOf { it }
        }

        fun max(): Int {
            return listOf(x, y, z)
                .map { it.last }
                .maxOf { it }
        }
    }
}