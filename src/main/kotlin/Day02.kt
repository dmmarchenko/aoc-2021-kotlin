object Day02 {

    fun part1(input: List<String>): Int {
        var horizontal = 0
        var depth = 0

        input.forEach {
            val (direction, value) = it.split(" ")
            val delta = value.toInt()
            when (direction) {
                "forward" -> horizontal += delta
                "down" -> depth += delta
                "up" -> depth -= delta
            }
        }

        return horizontal * depth
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        input.forEach {
            val (direction, value) = it.split(" ")
            val delta = value.toInt()
            when (direction) {
                "forward" -> {
                    horizontal += delta
                    depth += aim * delta
                }
                "down" -> aim += delta
                "up" -> aim -= delta
            }
        }

        return horizontal * depth
    }
}