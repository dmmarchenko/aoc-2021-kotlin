object Day04 {

    fun part1(input: List<String>): Int {
        val bingoInput = parseInput(input)

        for (command in bingoInput.commands) {
            bingoInput.boards.forEach { board ->
                board.markNumber(command)
                if (board.isWon()) {
                    return board.getScore(command)
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val (boards, commands) = parseInput(input)
        val winScores = mutableListOf<Int>()

        for (command in commands) {
            boards
                .filter { board -> !board.isWon() }
                .forEach { board ->
                    board.markNumber(command)
                    if (board.isWon()) {
                        winScores.add(board.getScore(command))
                    }
                }
        }
        return winScores.last()
    }

    private fun parseInput(input: List<String>): BingoInput {
        val commands = input.first()
            .split(",")
            .map { it.toInt() }

        val boards = input.drop(1)
            .filter { it.isNotBlank() }
            .windowed(size = 5, step = 5)
            .mapIndexed { index, lines -> toBoard(index, lines) }

        return BingoInput(boards, commands)
    }

    private fun toBoard(id: Int, rows: List<String>): Board {
        val fields = rows.map {
            it.trim()
                .split(" ")
                .filter { token -> token.isNotBlank() }
                .map { token -> Cell(token.toInt()) }
        }
        return Board(id, fields)
    }

    class Board(val id: Int, val field: List<List<Cell>>) {
        fun markNumber(number: Int) {
            this.field.forEach { row ->
                row.forEach {
                    if (it.value == number) {
                        it.marked = true
                    }
                }
            }
        }

        fun isWon(): Boolean {
            field.forEach { row ->
                val allRowIsMarked = row.all { it.marked }
                if (allRowIsMarked) {
                    return true
                }
            }

            for (column in 0..4) {
                val allColumnIsMarked = (0..4)
                    .map { row ->
                        field[row][column]
                    }
                    .all { it.marked }
                if (allColumnIsMarked) {
                    return true
                }
            }

            return false
        }

        fun getScore(command: Int): Int {
            val sumOfNotMarked = this.field
                .flatten()
                .filter { !it.marked }
                .sumOf { it.value }
            return command * sumOfNotMarked
        }
    }

    data class Cell(val value: Int, var marked: Boolean = false)

    data class BingoInput(val boards: List<Board>, val commands: List<Int>)
}