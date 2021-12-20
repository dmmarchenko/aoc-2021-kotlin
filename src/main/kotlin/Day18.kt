object Day18 {

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
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

        fun traverse() {
            traverse(0, this)
        }

        fun explode() {
            traverse(0, this)
        }
    }

    fun traverse(level: Int, node: Node?) {
        if (node == null) {
            return
        }

        println("$level) ${node.value}")

        val leftValue = node.left?.value
        val rightValue = node.right?.value

        if (level == 3 && leftValue != null && rightValue != null) {
            addToLeft(node, leftValue)
            addToRight(node, rightValue)
            node.left = null
            node.right = null
            node.value = 0
        }

        traverse(level + 1, node.left)
        traverse(level + 1, node.right)
    }

    fun addToLeft(node: Node, v: Long) {
        var prev = null
        var n = node

        while (n.parent != null && n == n.parent) {

        }
    }

    fun addToRight(node: Node, v: Long) {
    }
}