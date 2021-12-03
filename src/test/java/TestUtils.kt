import java.io.File

fun readInput(name: String) = File("src/test/resources", "$name.txt").readLines()