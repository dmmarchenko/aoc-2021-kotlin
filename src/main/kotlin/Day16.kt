object Day16 {

    fun part1(input: List<String>): Int {
        val binary = toBinaryCode(input)
        val packet = parsePacket(binary, 0)
        return packet.getTotalVersion()
    }

    fun part2(input: List<String>): Long {
        val binary = toBinaryCode(input)
        val packet = parsePacket(binary, 0)
        return packet.calculate()
    }

    private fun toBinaryCode(input: List<String>): String {
        return input.first().toCharArray()
            .asSequence()
            .map { it.toString() }
            .map { Integer.parseInt(it, 16) }
            .map { it.toString(2) }
            .map { it.padStart(4, '0') }
            .joinToString(separator = "")
    }


    private fun parsePacket(binary: String, startIndex: Int): Packet {
        val version = readBitsToInt(binary, startIndex, 3)
        val type = readBitsToInt(binary, startIndex + 3, 3)

        if (type == 4) {
            return parseLiteral(version, binary, startIndex + 6)
        }

        return parseOperator(version, type, binary, startIndex + 6)
    }

    private fun parseOperator(version: Int, type: Int, binary: String, startIndex: Int): OperatorPacket {
        val lengthType = readBitsToInt(binary, startIndex, 1)

        if (lengthType == 0) {
            return parseLengthType0(startIndex, binary, type, version)
        }

        return parseLengthType1(binary, startIndex, type, version)
    }

    private fun parseLengthType1(binary: String, startIndex: Int, type: Int, version: Int): OperatorPacket {
        val numberOfSubPackets = readBitsToInt(binary, startIndex + 1, 11)
        val childPackets = mutableListOf<Packet>()
        var i = startIndex + 12
        repeat(numberOfSubPackets) {
            val packet = parsePacket(binary, i)
            i = packet.binaryEndIndex
            childPackets += packet
        }

        return OperatorPacket(type, version, childPackets, i)
    }

    private fun parseLengthType0(startIndex: Int, binary: String, type: Int, version: Int): OperatorPacket {
        val totalLength = readBitsToInt(binary, startIndex + 1, 15).toInt()
        var i = startIndex + 16
        val endIndex = i + totalLength
        var packet: Packet
        val childPackets = mutableListOf<Packet>()

        do {
            packet = parsePacket(binary, i)
            i = packet.binaryEndIndex
            childPackets += packet
        } while (packet.binaryEndIndex < endIndex)

        return OperatorPacket(type, version, childPackets, endIndex)
    }

    private fun parseLiteral(version: Int, binary: String, startIndex: Int): LiteralPacket {
        var i = startIndex
        var literalBinary = ""
        var moreBlocksToRead: Boolean

        do {
            val block = binary.substring(i, i + 5)
            i += 5
            literalBinary += block.substring(1)
            moreBlocksToRead = block.startsWith("1")
        } while (moreBlocksToRead)

        val value = literalBinary.toLong(2)
        return LiteralPacket(value, version, i)
    }

    private fun readBitsToInt(binary: String, i: Int, bits: Int): Int = binary.substring(i, i + bits).toInt(2)

    abstract class Packet(val version: Int, val binaryEndIndex: Int) {
        open fun getTotalVersion(): Int {
            return version
        }

        abstract fun calculate(): Long
    }

    class LiteralPacket(private val value: Long, version: Int, binaryEndIndex: Int) : Packet(version, binaryEndIndex) {
        override fun getTotalVersion(): Int {
            return version
        }

        override fun calculate(): Long {
            return value
        }
    }

    class OperatorPacket(val type: Int, version: Int, val subPackets: List<Packet>, binaryEndIndex: Int) : Packet(version, binaryEndIndex) {
        override fun getTotalVersion(): Int {
            return version + subPackets.sumOf { it.getTotalVersion() }
        }

        override fun calculate(): Long {
            return when (type) {
                0 -> subPackets.sumOf { it.calculate() }
                1 -> subPackets.map { it.calculate() }.reduce { acc, i -> acc * i }
                2 -> subPackets.minOf { it.calculate() }
                3 -> subPackets.maxOf { it.calculate() }
                5 -> if (subPackets[0].calculate() > subPackets[1].calculate()) 1 else 0
                6 -> if (subPackets[0].calculate() < subPackets[1].calculate()) 1 else 0
                7 -> if (subPackets[0].calculate() == subPackets[1].calculate()) 1 else 0
                else -> 0
            }
        }
    }
}