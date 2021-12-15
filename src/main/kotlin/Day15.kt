import java.util.*

object Day15 {

    fun part1(input: List<String>): Int {
        val graph = readGraph(input)
        return graph.shortestPath()
    }

    fun part2(input: List<String>): Int {
        val graph = readGraph(input)
        val scaledGraph = scaleGraph(graph)
        return scaledGraph.shortestPath()
    }

    private fun readGraph(input: List<String>): Graph {
        val cells = input.mapIndexed { row, line ->
            line.toCharArray()
                .mapIndexed { column, ch ->
                    Cell(row, column, ch.digitToInt())
                }
                .toTypedArray()
        }.toTypedArray()

        return Graph(cells)
    }

    private fun scaleGraph(g: Graph): Graph {
        val cells = Array(g.rows * 5) { row ->
            Array(g.columns * 5) { column ->
                scaleCell(g, row, column)
            }
        }
        return Graph(cells)
    }

    private fun scaleCell(g: Graph, row: Int, column: Int): Cell {
        val corrCell = g.cells[row % g.rows][column % g.columns]
        val vertShift = row / g.rows
        val horShift = column / g.columns
        val v = inc(corrCell.v, vertShift + horShift)
        return Cell(row, column, v)
    }

    private fun inc(l: Int, inc: Int): Int {
        var lInc = l
        repeat(inc) {
            lInc = incLevel(lInc)
        }
        return lInc
    }

    private fun incLevel(l: Int): Int {
        return if (l < 9) l + 1 else 1
    }

    class Graph(val cells: Array<Array<Cell>>) {
        val rows = cells.size
        val columns = cells.first().size

        fun shortestPath(): Int {
            val queue = PriorityQueue(compareBy<Cell> { it.dist })
            cells[0][0].dist = 0

            forEachCell {
                queue.add(it)
            }

            while (queue.isNotEmpty()) {
                val v = queue.poll()

                if (!v.visited) {
                    v.visited = true
                    forEachNeighbor(v) { neighbor ->
                        val dist = v.dist + neighbor.v
                        if (dist < neighbor.dist) {
                            queue.remove(neighbor)
                            neighbor.dist = dist
                            queue += neighbor
                        }
                    }
                }
            }

            return cells[rows - 1][columns - 1].dist
        }

        private fun forEachCell(action: (Cell) -> Unit) {
            for (row in this.cells) {
                for (cell in row) {
                    action(cell)
                }
            }
        }

        private fun forEachNeighbor(cell: Cell, action: (Cell) -> Unit) {
            val (row, col) = cell
            sequenceOf(
                row - 1 to col,
                row to col + 1,
                row + 1 to col,
                row to col - 1
            )
                .filter { it.first in (0 until rows) }
                .filter { it.second in (0 until columns) }
                .forEach {
                    action(cells[it.first][it.second])
                }
        }

        fun print() {
            for (row in this.cells) {
                for (cell in row) {
                    print(cell.v)
                }
                println()
            }
        }
    }

    data class Cell(val row: Int, val col: Int, val v: Int, var visited: Boolean = false, var dist: Int = Int.MAX_VALUE)
}