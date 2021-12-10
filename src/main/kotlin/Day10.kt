import org.nield.kotlinstatistics.median
import java.util.*

object Day10 {

    private val scores = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )
    private val completionScores = mapOf(
        ')' to 1L,
        ']' to 2L,
        '}' to 3L,
        '>' to 4L
    )
    private val closedToOpen = mapOf(
        ')' to '(',
        ']' to '[',
        '}' to '{',
        '>' to '<'
    )
    private val openToClosed = closedToOpen
        .map { it.value to it.key }
        .toMap()
    private val openBrackets = setOf('(', '[', '{', '<')

    fun part1(input: List<String>): Int {
        return input.sumOf { invalidScore(it) }
    }

    fun part2(input: List<String>): Long {
        val scores = input.filter { invalidScore(it) == 0 }
            .map { completionString(it) }
            .map { completionScore(it) }
        val middleIndex = scores.size / 2

        return scores
            .sorted()[middleIndex]
    }

    private fun invalidScore(line: String): Int {
        val stack = Stack<Char>()

        for (c in line.toCharArray()) {
            if (c in openBrackets) {
                stack.push(c)
            } else {
                val lastChar = stack.pop()
                if (closedToOpen[c] != lastChar) {
                    return scores[c]!!
                }
            }
        }

        return 0
    }

    private fun completionString(line: String): String {
        val stack = Stack<Char>()

        for (c in line.toCharArray()) {
            if (c in openBrackets) {
                stack.push(c)
            } else {
                stack.pop()
            }
        }

        return stack.map { openToClosed[it] }
            .reversed()
            .joinToString(separator = "")
    }

    private fun completionScore(line: String): Long {
        val scores = line.toCharArray()
            .map { completionScores[it]!! }
        return buildList {
            add(0)
            addAll(scores)
        }
            .reduce { acc, i -> acc * 5 + i }
    }
}