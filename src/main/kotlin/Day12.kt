import java.util.function.BiPredicate

object Day12 {

    fun part1(input: List<String>): Int {
        val graph = parseGraph(input)
        return dfs(graph) { v, path -> v == v.uppercase() || v !in path }
    }

    fun part2(input: List<String>): Int {
        val graph = parseGraph(input)
        return dfs(graph) { v, path -> v == v.uppercase() || canBeUsed(path, v) }
    }

    private fun canBeUsed(path: List<String>, v: String): Boolean {
        val visits = path
            .filter { it == it.lowercase() }
            .groupBy { it }
            .mapValues { it.value.size }
        val alreadyTwoVisits = visits.values.any { it > 1 }
        val allowedVisits = if (alreadyTwoVisits) 1 else 2

        return (visits[v] ?: 0) < allowedVisits
    }

    private fun dfs(graph: Map<String, List<String>>, visitPredicate: BiPredicate<String, List<String>>): Int {
        val paths = mutableListOf<List<String>>()
        val path = mutableListOf<String>().apply {
            add("start")
        }

        dfsInternal("start", graph, path, paths, visitPredicate)

        return paths.size
    }

    private fun dfsInternal(
        v: String,
        graph: Map<String, List<String>>,
        path: MutableList<String>,
        paths: MutableList<List<String>>,
        visitPredicate: BiPredicate<String, List<String>>
    ) {
        if (v == "end") {
            paths += mutableListOf<String>().apply { addAll(path) }
            return
        }

        graph[v]!!
            .filter { visitPredicate.test(it, path) }
            .forEach {
                path += it
                dfsInternal(it, graph, path, paths, visitPredicate)
                path.removeLast()
            }
    }

    private fun parseGraph(input: List<String>): Map<String, List<String>> {
        val edges = input.map {
            val (fromV, toV) = it.split("-")
            fromV to toV
        }
        val backEdges = edges
            .map { it.flip() }

        return listOf(edges, backEdges)
            .flatten()
            .filter { it.second != "start" }
            .filter { it.first != "end" }
            .groupBy({ it.first }) { it.second }
    }

    private fun <K, V> Pair<K, V>.flip(): Pair<V, K> {
        return this.second to this.first
    }
}