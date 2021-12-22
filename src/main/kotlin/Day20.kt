object Day20 {

    val light = '#'
    val dark = '.'

    fun part1(input: List<String>): Int {
        val parsedInput = parseInput(input)
        val scaled = parsedInput.img.scale()
        val enhanced = scaled.enhance(parsedInput.algo)
            .enhance(parsedInput.algo)
        return enhanced.numberOfLightPixels()
    }

    fun part2(input: List<String>): Int {
        val parsedInput = parseInput(input)
        var img = parsedInput.img

        repeat(3) {
            img = img.scale()
        }
        println("Scaling completed")

        repeat(50) {
            img = img.enhance(parsedInput.algo)
            println("Enhanced: $it times")
        }
        return img.numberOfLightPixels()
    }

    private fun parseInput(input: List<String>): Input {
        val algoCode = input.first().parsePixels()
        val img = input.drop(2).map { it.parsePixels() }.toTypedArray()
        return Input(algoCode, Image(img))
    }

    private fun String.parsePixels(): Array<Boolean> {
        return this.toCharArray().map { it == light }.toTypedArray()
    }

    class Image(private val pixels: Array<Array<Boolean>>, private val backDark: Boolean = true) {
        private val width = pixels.first().size
        private val height = pixels.size

        fun scale(): Image {
            val img = this.pixels
            val rows = height
            val columns = width
            val scaledRows = rows * 3
            val scaledColumns = columns * 3
            val scaledPixels = Array(scaledRows) { rowNum ->
                if (rowNum < rows || rowNum >= rows * 2) {
                    Array(scaledColumns) { false }
                } else {
                    Array(scaledColumns) { columnNum ->
                        if (columnNum < columns || columnNum >= columns * 2) {
                            false
                        } else {
                            img[rowNum - rows][columnNum - columns]
                        }
                    }
                }
            }
            return Image(scaledPixels)
        }

        fun print() {
            for (row in this.pixels) {
                for (p in row) {
                    print(if (p) light else dark)
                }
                println()
            }
        }

        fun enhance(algo: Array<Boolean>): Image {
            val enhancedPixels = Array(height) {
                Array(width) { false }
            }

            for (row in 0 until height) {
                (0 until width).toList().parallelStream()
                    .forEach { column ->
                        enhancedPixels[row][column] = enhancedPixel(row, column, algo)
                    }
            }

            return Image(enhancedPixels, backDark = !enhancedPixels[0][0])
        }

        private fun enhancedPixel(row: Int, column: Int, algo: Array<Boolean>): Boolean {
            val binary = listOf(
                row - 1 to column - 1,
                row - 1 to column,
                row - 1 to column + 1,
                row to column - 1,
                row to column,
                row to column + 1,
                row + 1 to column - 1,
                row + 1 to column,
                row + 1 to column + 1
            )
                .joinToString(separator = "") { (r, c) ->
                    if (r !in (0 until height) || c !in (0 until width)) {
                        if (backDark) "0" else "1"
                    } else {
                        if (this.pixels[r][c]) "1" else "0"
                    }
                }
            val decimal = binary.toInt(2)
            return algo[decimal]
        }

        fun numberOfLightPixels(): Int {
            return pixels.sumOf { row -> row.count { it } }
        }
    }

    data class Input(val algo: Array<Boolean>, val img: Image)
}