object Day14 {

    fun part1(input: List<String>): Long {
        return process(input, 10)
    }

    fun part2(input: List<String>): Long {
        return process(input, 40)
    }

    private fun process(input: List<String>, iterations: Int): Long {
        val (template, rules) = readInput(input)
        var tokens = template.windowed(2, 1)
            .groupBy { it }
            .mapValues { it.value.size.toLong() }

        repeat(iterations) {
            tokens = iterate(tokens, rules)
        }

        return countChars(tokens, template)
    }

    private fun countChars(tokens: Map<String, Long>, startingTemplate: String): Long {
        val chToCount = tokens.flatMap {
            listOf(
                it.key[0].toString() to it.value,
                it.key[1].toString() to it.value
            )
        }
            .groupBy({ it.first }) { it.second }
            .mapValues { it.value.sum() }

        val max = chToCount.maxByOrNull { it.value }!!
        val maxV = max.value / 2 + increment(max.toPair(), startingTemplate)

        val min = chToCount.minByOrNull { it.value }!!
        val minV = min.value / 2 + increment(min.toPair(), startingTemplate)

        return maxV - minV
    }

    private fun increment(pair: Pair<String, Long>, template: String): Long {
        if (template.startsWith(pair.first) || template.endsWith(pair.first)) {
            return 1
        }
        return 0
    }

    private fun iterate(tokens: Map<String, Long>, rules: Map<String, Pair<String, String>>): Map<String, Long> {
        val newMap = mutableMapOf<String, Long>().apply {
            putAll(tokens)
        }

        tokens
            .forEach { (token, count) ->
                val current = newMap[token]!!
                newMap[token] = current - count
                val (first, second) = rules[token]!!
                newMap.inc(first, count)
                newMap.inc(second, count)
            }

        return newMap.filter { it.value > 0 }
    }

    private fun MutableMap<String, Long>.inc(k: String, inc: Long) {
        this.computeIfPresent(k) { _, i -> i + inc }
        this.computeIfAbsent(k) { inc }
    }

    private fun readInput(input: List<String>): Pair<String, Map<String, Pair<String, String>>> {
        val template = input.first()
        val rules = input.drop(2)
            .associate {
                val (f, t) = it.split(" -> ")
                f to (f[0] + t to t + f[1])
            }
        return template to rules
    }
}