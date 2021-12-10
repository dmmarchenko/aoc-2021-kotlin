object Day09 {

    fun part1(input: List<String>): Int {
        val arr = readInput(input)
        val rows = arr.size
        val columns = arr.first().size
        var riskLevel = 0

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                val current = arr[i][j].v
                val localMin = arr
                    .adjacentCells(i, j)
                    .map { arr[it.first][it.second] }
                    .map { it.v }
                    .all { current < it }
                if (localMin) {
                    riskLevel += current + 1
                }
            }
        }

        return riskLevel
    }

    fun part2(input: List<String>): Int {
        val arr = readInput(input)

        val rows = arr.size
        val columns = arr.first().size
        val basins = mutableListOf<MutableList<Int>>()
        basins.add(mutableListOf())

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                dfs(arr, i, j, basins)
                if (basins.last().isNotEmpty()) {
                    basins.add(mutableListOf())
                }
            }
        }

        return basins
            .map { it.size }
            .sortedDescending()
            .take(3)
            .reduce { acc, i -> acc * i }
    }

    private fun dfs(arr: Array<Array<Cell>>, i: Int, j: Int, basins: MutableList<MutableList<Int>>) {
        val cell = arr[i][j]
        if (!cell.visited && cell.v != 9) {
            cell.visited = true
            basins.last().add(cell.v)
            arr.adjacentCells(i, j)
                .forEach {
                    dfs(arr, it.first, it.second, basins)
                }
        }
    }

    private fun readInput(input: List<String>): Array<Array<Cell>> {
        return Array(input.size) { index ->
            input[index].toList()
                .map { Cell(it.digitToInt()) }
                .toTypedArray()
        }
    }

    private fun Array<Array<Cell>>.adjacentCells(i: Int, j: Int): List<Pair<Int, Int>> {
        return listOf(
            i - 1 to j,
            i to j - 1,
            i to j + 1,
            i + 1 to j
        )
            .filter { this.inBound(it) }
    }

    private fun Array<Array<Cell>>.inBound(coords: Pair<Int, Int>): Boolean {
        val rows = this.size
        val columns = this.first().size
        return coords.first in 0 until rows && coords.second in 0 until columns
    }

    data class Cell(val v: Int, var visited: Boolean = false)
}