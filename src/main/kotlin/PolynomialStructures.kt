package pl.student

data class Polynomial(val exponentToCoefficient: Map<Int, PolynomialCoefficient>)

data class PolynomialCoefficient(val value: Int) {

    val alphaExponent: Int
        get() = GF256Exponents.valueToExponent[value]!!

    init {
        require(value in 0..256) { "Value must be in GF(256)" }
    }

    companion object {
        fun ofAlpha(exponent: Int): PolynomialCoefficient {
            require(exponent in 0..256) { "Exponent must be in GF(256)" }
            return PolynomialCoefficient(GF256Exponents.exponentToValue[exponent]!!)
        }
    }
}
