object Day06 {

    fun part1(input: List<String>): Long {
        val counts = readInput(input)

        repeat(80) {
            iterate(counts)
        }

        return counts.sum()
    }

    fun part2(input: List<String>): Long {
        val counts = readInput(input)

        repeat(256) {
            iterate(counts)
        }

        return counts.sum()
    }

    private fun readInput(input: List<String>): Array<Long> {
        val counts = Array(10) { 0L }
        val ageToCount = input.first()
            .split(",")
            .map { it.toInt() }
            .toMutableList()
            .groupBy { it }
            .mapValues { it.value.size }
        ageToCount.forEach { (index, count) ->
            counts[index] = count.toLong()
        }
        return counts
    }

    private fun iterate(counts: Array<Long>) {
        val zeroAge = counts[0]

        for (i in 0..7) {
            counts[i] = counts[i + 1]
        }
        counts[8] = zeroAge
        counts[6] += zeroAge
    }
}