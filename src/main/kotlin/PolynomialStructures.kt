package pl.student

import java.util.SortedMap

data class Polynomial(val exponentToCoefficient: SortedMap<Int, PolynomialCoefficient>) {
    val leadTerm: Map.Entry<Int, PolynomialCoefficient>
        get() = exponentToCoefficient.lastEntry()

    companion object {
        fun ofSingleExponent(value: Int): Polynomial {
            return Polynomial(sortedMapOf(value to PolynomialCoefficient(1)))
        }
    }
}

data class PolynomialCoefficient(val value: Int) {

    val alphaExponent: Int
        get() = GF256Exponents.valueToExponent[value]!!

    init {
        require(value in 0..256) { "Value must be in GF(256)" }
    }

    companion object {
        fun ofAlpha(exponent: Int): PolynomialCoefficient {
            require(exponent in 0 until 256) { "Exponent must be in GF(256)" }
            return PolynomialCoefficient(GF256Exponents.exponentToValue[exponent]!!)
        }
    }
}
