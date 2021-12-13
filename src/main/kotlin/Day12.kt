object Day12 {

    fun part1(input: List<String>): Int {
        val graph = parseGraph(input)
        dfs(graph)

        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    private fun dfs(graph: Map<String, List<String>>) {
        val visited = mutableSetOf<String>()
        val paths = mutableListOf<List<String>>()
        val path = mutableListOf<String>()

        dfsInternal("start", graph, visited, path, paths)

        paths.forEach {
            println(it)
        }

    }

    private fun dfsInternal(
        v: String,
        graph: Map<String, List<String>>,
        visited: MutableSet<String>,
        path: MutableList<String>,
        paths: MutableList<List<String>>
    ) {
        if (v !in visited) {
            print("$v ")

            //if (v == v.lowercase()) {
            //    visited += v
            //}

            if (v == "end") {
                paths += mutableListOf<String>()
                    .apply { addAll(path) }
            } else {
                graph[v]?.forEach {
                    path += v
                    dfsInternal(it, graph, visited, path, paths)
                    path.removeLast()
                }

            }
        }
    }

    private fun parseGraph(input: List<String>): Map<String, List<String>> {
        val edges = input.map {
            val (fromV, toV) = it.split("-")
            fromV to toV
        }
        val backEdges = edges
            .filter { it.first != "start" }
            .filter { it.second != "end" }
            .map { it.flip() }

        return listOf(edges, backEdges)
            .flatten()
            .groupBy({ it.first }) { it.second }
    }

    private fun <K, V> Pair<K, V>.flip(): Pair<V, K> {
        return this.second to this.first
    }
}