package pl.student

object GF256Exponents {

    val exponentToValue: Map<Int, Int>
    val valueToExponent: Map<Int, Int>

    init {
        val initExponentToValue = mutableMapOf(0 to 1)
        val initValueToExponent = mutableMapOf(1 to 0)
        var currentValue = 1
        for (exponent in 1..255) {
            currentValue *= 2
            if (currentValue > 255) {
                currentValue = currentValue.xor(285)
            }
            initExponentToValue[exponent] = currentValue
            initValueToExponent[currentValue] = exponent
        }
        // In GF(256), 2^0 = 1 and 2^255 = 1, so 0 == 255, should not matter much
        initValueToExponent[1] = 0
        initExponentToValue[0] = 1

        exponentToValue = initExponentToValue
        valueToExponent = initValueToExponent
    }

}