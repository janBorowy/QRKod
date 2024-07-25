package pl.student

import kotlin.math.min

interface Encoder {
    val version: Int
    fun encode(input: String)
}

const val NUMERIC_MODE_INDICATOR: String = "0001"

class NumericEncoder(override val version: Int) : Encoder {

    override fun encode(input: String) {
        validateInput(input)
        val digitGroups = groupString(input, 3)

    }

    private fun validateInput(input: String) =
        require(input.all { it in '0'..'9' }) {"Cannot encode input"}
}
