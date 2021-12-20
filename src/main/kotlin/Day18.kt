import kotlin.math.max

object Day18 {

    fun part1(input: List<String>): Long {
        var sum = parseNumber(input.first())

        input.drop(1).forEach {
            val next = parseNumber(it)
            sum = sum.plus(next)
        }

        return sum.magnitude()
    }

    fun part2(input: List<String>): Long {
        var maxMagnitude = 0L

        for (i in input.indices) {
            for (j in input.indices) {
                if (i != j) {
                    val magnitude = parseNumber(input[i]).plus(parseNumber(input[j])).magnitude()
                    maxMagnitude = max(maxMagnitude, magnitude)
                }
            }
        }

        return maxMagnitude
    }

    fun parseNumber(str: String): Node {
        return parseNode(str, null)
    }

    private fun parseNode(str: String, parent: Node?): Node {
        if (!str.contains("[")) {
            return Node(parent, str.toLong())
        }

        val node = Node(parent)
        val comaIndex = findComaIndex(str)
        node.left = parseNode(str.substring(1, comaIndex), node)
        node.right = parseNode(str.substring(comaIndex + 1, str.length - 1), node)
        return node
    }

    private fun findComaIndex(str: String): Int {
        var brackets = 0
        str.toCharArray()
            .withIndex()
            .forEach { (index, ch) ->
                if (ch == '[') {
                    brackets++
                } else if (ch == ']') {
                    brackets--
                } else if (ch == ',' && brackets == 1) {
                    return index
                }
            }
        return 0
    }

    data class Node(var parent: Node?, var value: Long? = null, var left: Node? = null, var right: Node? = null) {
        fun magnitude(): Long {
            if (value != null) {
                return value!!
            }

            return 3 * left!!.magnitude() + 2 * right!!.magnitude()
        }

        fun plus(other: Node): Node {
            val newRoot = Node(null)
            newRoot.left = this
            this.parent = newRoot
            newRoot.right = other
            other.parent = newRoot
            newRoot.reduce()
            return newRoot
        }

        fun reduce() {
            var reducable: Boolean
            do {
                reducable = if (explode()) {
                    true
                } else {
                    split()
                }
            } while (reducable)
        }

        fun explode(): Boolean {
            val context = TraverseContext()
            traverse(0, this, context)
            return context.command != CommandType.EXPLODE
        }

        fun split(): Boolean {
            val context = TraverseContext(command = CommandType.SPLIT)
            traverse(0, this, context)
            return context.command != CommandType.SPLIT
        }

        fun asString(): String {
            val str = StringBuilder()
            traversePrint(0, this, str)
            return str.toString()
        }

        override fun toString(): String {
            return if (this.value != null) this.value.toString() else "null"
        }
    }

    private fun traversePrint(level: Int, node: Node, str: StringBuilder) {
        if (node.value != null) {
            str.append(node.value)
        }

        if (node.left != null) {
            str.append("[")
            traversePrint(level + 1, node.left!!, str)
            str.append(",")
        }

        if (node.right != null) {
            traversePrint(level + 1, node.right!!, str)
            str.append("]")
        }
    }

    private fun traverse(level: Int, node: Node?, context: TraverseContext) {
        if (node == null || context.command == CommandType.EXIT) {
            return
        }

        if (node.value != null) {
            context.visitedNodes += node

            if (context.command == CommandType.INC_RIGHT) {
                addToRight(node, context)
                return
            }

            if (context.command == CommandType.SPLIT && node.value!! > 9) {
                split(node, context)
                return
            }
        }

        if (context.command == CommandType.EXPLODE) {
            val leftValue = node.left?.value
            val rightValue = node.right?.value

            if (level >= 4 && leftValue != null && rightValue != null) {
                explode(leftValue, rightValue, context, node)
                return
            }
        }

        traverse(level + 1, node.left, context)
        traverse(level + 1, node.right, context)
    }

    private fun split(node: Node, context: TraverseContext) {
        val half = node.value!! / 2.0
        node.value = null
        val leftNode = Node(node, half.toLong())
        leftNode.parent = node
        node.left = leftNode
        val rightNode = Node(node, half.roundUp())
        rightNode.parent = node
        node.right = rightNode
        context.command = CommandType.EXIT
    }

    private fun Double.roundUp(): Long {
        val asLong = this.toLong()
        return if (this == asLong.toDouble()) asLong else asLong + 1
    }

    private fun explode(leftValue: Long, rightValue: Long, context: TraverseContext, node: Node) {
        addToLeft(leftValue, context.visitedNodes)
        node.left = null
        node.right = null
        node.value = 0
        context.command = CommandType.INC_RIGHT
        context.incValue = rightValue
    }

    private fun addToLeft(v: Long, nodesVisited: List<Node>) {
        if (nodesVisited.isNotEmpty()) {
            val leftItem = nodesVisited.last()
            leftItem.value = leftItem.value!! + v
        }
    }

    private fun addToRight(node: Node, context: TraverseContext) {
        node.value = node.value!! + context.incValue!!
        context.command = CommandType.EXIT
    }

    data class TraverseContext(
        var command: CommandType = CommandType.EXPLODE,
        var incValue: Long? = null,
        val visitedNodes: MutableList<Node> = mutableListOf()
    )

    enum class CommandType {
        EXPLODE, INC_RIGHT, EXIT, SPLIT
    }
}