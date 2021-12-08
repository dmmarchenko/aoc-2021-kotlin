object Day08 {

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line.split(" | ")
                .last()
                .split(" ")
                .map { it.length }
                .count { it in setOf(2, 3, 4, 7) }
        }
    }


    fun part2(input: List<String>): Int {
        return input.sumOf { decode(it) }
    }

    private fun decode(input: String): Int {
        val (train, code) = input.split(" | ")
        val dict = buildDictionary(train)
        return code.split(" ")
            .map { it.toCharArray().toSet() }
            .map { dict[it] }
            .joinToString(separator = "")
            .toInt()
    }

    private fun buildDictionary(train: String): Map<Set<Char>, Int> {
        val codes = train.split(" ")
            .map { it.toCharArray().toSet() }
        val c1 = codes.first { it.size == 2 }
        val c4 = codes.first { it.size == 4 }
        val c7 = codes.first { it.size == 3 }
        val c8 = codes.first { it.size == 7 }
        val a = (c7 - c1).first()
        val (s51, s52, s53) = codes.filter { it.size == 5 }
        val dg = s51.intersect(s52).intersect(s53) - a
        val g = (dg - c4).first()
        val d = (dg - g).first()
        val c0 = c8 - d
        val (c691, c692) = codes.filter { it.size == 6 }.filter { it != c0 }
        val ce = (c691 - c692) + (c692 - c691)
        val e = (ce - c1).first()
        val c = (ce - e).first()
        val f = (c1 - c).first()
        val b = (c4 - c - d - f).first()

        return mapOf(
            c0 to 0,
            c1 to 1,
            setOf(a, c, d, e, g) to 2,
            setOf(a, c, d, f, g) to 3,
            c4 to 4,
            setOf(a, b, d, f, g) to 5,
            setOf(a, b, d, e, f, g) to 6,
            c7 to 7,
            c8 to 8,
            setOf(a, b, c, d, f, g) to 9
        )
    }


}