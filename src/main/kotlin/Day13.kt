object Day13 {

    fun part1(input: List<String>): Int {
        val grid = parseGrid(input)
        val commands = parseCommands(input)

        val foldedGrid = fold(grid, commands.first())
        return foldedGrid.countDots()
    }

    fun part2(input: List<String>): Int {
        var grid = parseGrid(input)
        val commands = parseCommands(input)

        commands
            .forEach {
            grid = fold(grid, it)
        }

        grid.print()

        return grid.countDots()
    }

    private fun fold(grid: Array<Array<Boolean>>, fold: Fold): Array<Array<Boolean>> {
        if (fold.direction == FoldDirection.VERTICAL) {
            val firstHalf = grid.copyOfRange(0, fold.v)
            val secondHalf = grid.copyOfRange(fold.v + 1, grid.size)
            secondHalf.reverse()
            firstHalf.merge(secondHalf)
            return firstHalf
        }

        val firstHalf = grid.map { it.copyOfRange(0, fold.v) }
            .map { it.reversed().toTypedArray() }
            .toTypedArray()
        val secondHalf = grid.map { it.copyOfRange(fold.v + 1, it.size) }.toTypedArray()
        secondHalf.merge(firstHalf)

        return secondHalf
    }

    private fun parseGrid(input: List<String>): Array<Array<Boolean>> {
        val coords = input
            .filter { it.contains(",") }
            .map { line ->
                val (x, y) = line
                    .split(",")
                    .map { it.toInt() }
                x to y
            }
        val w = coords.maxOf { it.first } + 1
        val h = coords.maxOf { it.second } + 1

        val grid = Array(h) { Array(w) { false } }

        coords.forEach { (x, y) ->
            grid[y][x] = true
        }

        return grid
    }

    private fun parseCommands(input: List<String>): List<Fold> {
        return input
            .filter { it.contains("fold") }
            .map { it.substring(11) }
            .map { line ->
                val (coord, v) = line.split("=")
                val direction = if (coord == "x") FoldDirection.HORIZONTAL else FoldDirection.VERTICAL
                Fold(v.toInt(), direction)
            }
    }

    private fun Array<Array<Boolean>>.print() {
        for (row in this) {
            for (cell in row) {
                print(if (cell) "#" else ".")
            }
            println()
        }
    }

    private fun Array<Array<Boolean>>.countDots(): Int {
        var cnt = 0

        for (row in this) {
            for (cell in row) {
                if (cell) cnt++
            }
        }

        return cnt
    }

    private fun Array<Array<Boolean>>.merge(arr: Array<Array<Boolean>>) {
        this.forEachIndexed { i, row ->
            row.indices.forEach { j ->
                if (arr[i][j]) {
                    row[j] = true
                }
            }
        }
    }

    data class Fold(val v: Int, val direction: FoldDirection)

    enum class FoldDirection {
        HORIZONTAL, VERTICAL
    }
}