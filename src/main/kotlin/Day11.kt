object Day11 {

    fun part1(input: List<String>): Int {
        val grid = readInput(input)

        repeat(100) {
            grid.iterate()
        }

        return grid.totalFlashes()
    }

    fun part2(input: List<String>): Int {
        val grid = readInput(input)

        var iterations = 0
        while(!grid.iterate()) {
            iterations++
        }

        return iterations + 1
    }

    private fun readInput(input: List<String>): Grid {
        val field = input
            .map { line ->
                line.toCharArray()
                    .map { it.digitToInt() }
                    .map { Cell(it) }
                    .toTypedArray()
            }
            .toTypedArray()
        return Grid(field)
    }

    class Grid(private val field: Array<Array<Cell>>) {

        private val rows = field.size
        private val columns = field.first().size

        fun iterate(): Boolean {
            forEachCell { cell, _, _ -> cell.v++ }

            var moreFlashesToProcess: Boolean
            do {
                moreFlashesToProcess = false
                forEachCell { cell, row, column ->
                    if (cell.v > 9 && !cell.flashed) {
                        cell.flashed = true
                        cell.flashes++
                        moreFlashesToProcess = true
                        visitAdjacent(row, column) {
                            it.v++
                        }
                    }
                }
            } while (moreFlashesToProcess)


            var totalFlashes = 0
            forEachCell { cell, _, _ ->
                if (cell.flashed) {
                    totalFlashes++
                    cell.flashed = false
                    cell.v = 0
                }
            }
            return totalFlashes == rows * columns
        }

        private fun forEachCell(action: (cell: Cell, row: Int, column: Int) -> Unit) {
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    action(field[i][j], i, j)
                }
            }
        }

        private fun visitAdjacent(i: Int, j: Int, action: (cell: Cell) -> Unit) {
            (-1..1).forEach { dy ->
                (-1..1).forEach { dx ->
                    val row = i + dy
                    val column = j + dx
                    if (row in 0 until rows && column in 0 until columns) {
                        action(field[row][column])
                    }
                }
            }
        }

        fun print() {
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    print(field[i][j].v)
                }
                println()
            }
            println()
        }

        fun totalFlashes(): Int {
            var total = 0
            forEachCell { cell, _, _ -> total += cell.flashes }
            return total
        }
    }

    data class Cell(var v: Int, var flashed: Boolean = false, var flashes: Int = 0)
}