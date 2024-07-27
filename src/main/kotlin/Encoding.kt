package pl.student

interface Encoder {
    fun encode(input: String, version: Version): Bits
}

const val GROUP_SIZE = 3

object NumericEncoder : Encoder {

    private val NUMERIC_MODE_INDICATOR: Bits = Bits.of(0, 0, 0, 1)

    override fun encode(input: String, version: Version): Bits {
        validateInput(input)
        val digitGroups = groupString(input, GROUP_SIZE)
        val numbers = digitGroups.map {convertGroupToBits(it)}.toTypedArray()
        val countIndicator = encodeInteger(input.length, version.numberOfCountBits(InputMode.NUMERIC))
        return Bits.concatonated(NUMERIC_MODE_INDICATOR, countIndicator, *numbers)
    }

    private fun convertGroupToBits(str: String): Bits {
        val size = when (str.length) {
            3 -> 10
            2 -> 7
            1 -> 4
            else -> throw IllegalStateException("Groups of more than 3 digits were not expected")
        }
        return encodeInteger(str.toInt(), size)
    }

    private fun encodeInteger(int: Int, resultSize: Int): Bits {
        val binary = int.toBinary()
        require (resultSize >= binary.size) {"Not enough space to encode binary"}
        val result = Bits.zeroes(resultSize)
        result.or(binary)
        return result
    }

    private fun validateInput(input: String) =
        require(input.all { it in '0'..'9' }) {"Cannot encode input"}
}

object AlphanumericEncoder : Encoder {


    override fun encode(input: String, version: Version): Bits {
        TODO("Not yet implemented")
    }


}
