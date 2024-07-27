package pl.student

interface Encoder {
    fun encode(input: String, version: Version): Bits
}

const val GROUP_SIZE = 3
const val CANNOT_ENCODE_INPUT_MESSAGE = "Cannot encode input"
const val NOT_ENOUGH_SPACE_TO_ENCODE_BINARY_MESSAGE = "Not enough space to encode binary"

internal object NumericEncoder : Encoder {

    private val NUMERIC_MODE_INDICATOR: Bits = Bits.of("0001")

    override fun encode(input: String, version: Version): Bits {
        validateInput(input)
        val digitGroups = input.groupString(GROUP_SIZE)
        val numbers = digitGroups.map { convertGroupToBits(it) }.toTypedArray()
        val countIndicator = input.length.toBinary(version.numberOfCountBits(InputMode.NUMERIC))
        return Bits.concatonated(NUMERIC_MODE_INDICATOR, countIndicator, *numbers)
    }

    private fun convertGroupToBits(str: String): Bits {
        val size = when (str.length) {
            3 -> 10
            2 -> 7
            1 -> 4
            else -> throw IllegalStateException("Groups of more than 3 digits were not expected")
        }
        return str.toInt().toBinary(size)
    }

    private fun validateInput(input: String) =
        require(isNumeric(input)) { CANNOT_ENCODE_INPUT_MESSAGE }
}

internal object AlphanumericEncoder : Encoder {

    private val ALPHANUMERIC_MODE_INDICATOR = Bits.of("0010")

    override fun encode(input: String, version: Version): Bits {
        validateInput(input)
        val groups = input.groupString(2)
        val values = groups.map { encodeAlphanumericGroup(it) }
        val countIndicator = input.length.toBinary(version.numberOfCountBits(InputMode.ALPHANUMERIC))
        return Bits.concatonated(ALPHANUMERIC_MODE_INDICATOR, countIndicator, *values.toTypedArray())
    }

    private fun encodeAlphanumericGroup(group: String): Bits {
        return when (group.length) {
            2 -> encodeAlphanumericForDouble(group[0] to group[1])
            1 -> encodeAlphanumericForSingle(group[0])
            else -> throw IllegalArgumentException(NOT_ENOUGH_SPACE_TO_ENCODE_BINARY_MESSAGE)
        }
    }

    private fun encodeAlphanumericForDouble(pair: Pair<Char, Char>): Bits {
        return (getAlphanumericValue(pair.first) * 45 + getAlphanumericValue(pair.second)).toBinary(11)
    }

    private fun encodeAlphanumericForSingle(char: Char): Bits {
        return getAlphanumericValue(char).toBinary(6)
    }

    private fun validateInput(input: String) {
        require(isAlphanumeric(input)) { CANNOT_ENCODE_INPUT_MESSAGE }
    }
}

internal object BinaryEncoder : Encoder {

    private val BINARY_MODE_INDICATOR: Bits = Bits.of("0100")

    override fun encode(input: String, version: Version): Bits {
        validateInput(input)
        val binaryValues = bitsOfByteBuffer(Charsets.ISO_8859_1.encode(input))
        val countIndicator = input.length.toBinary(version.numberOfCountBits(InputMode.BINARY))
        return Bits.concatonated(BINARY_MODE_INDICATOR, countIndicator, binaryValues)
    }

    private fun validateInput(input: String) {
        require(isBinary(input)) { CANNOT_ENCODE_INPUT_MESSAGE }
    }

}