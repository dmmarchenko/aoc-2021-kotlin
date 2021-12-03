object Day01 {
    fun part1(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .windowed(2)
            .count { (l, r) -> l < r }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .windowed(4)
            .count {
                it[0] < it[3]
            }

    }
}