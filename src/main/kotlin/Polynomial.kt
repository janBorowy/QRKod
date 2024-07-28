package pl.student

import kotlin.math.pow

fun Codeword.toInteger(): Int {
    return bits.mapIndexed { i, bit ->
        if (bit.on) 2.0.pow(7 - i).toInt() else 0
    }.sum()
}

fun convertToMessagePolynomial(codewords: Codewords): Polynomial {
    return codewords.mapIndexed { i, codeword ->
        codewords.size - 1 - i to codeword.toInteger()
    }.toMap().toPolynomial()
}

fun calculateGeneratorPolynomial(n: Int): Polynomial {
    require(n > 0) { "n must be positive" }
    var result = Polynomial(
        mapOf(
            1 to PolynomialCoefficient.ofAlpha(0),
            0 to PolynomialCoefficient.ofAlpha(0)
        )
    )
    for (i in 2..n) {
        val toMultiplyBy = Polynomial(mapOf(
            1 to PolynomialCoefficient.ofAlpha(0),
            0 to PolynomialCoefficient.ofAlpha(i - 1)
        ))
        result = result.multiply(toMultiplyBy)
    }
    return result
}

fun Polynomial.multiply(other: Polynomial): Polynomial {
    val addends = exponentToCoefficient.map { first ->
        other.exponentToCoefficient.map { second ->
            first.multiply(second)
        }
    }.flatten()
    val polynomialMap: MutableMap<Int, Int> = mutableMapOf()
    addends.forEach { (exponent, value) ->
        if (polynomialMap.containsKey(exponent)) {
            polynomialMap[exponent] = polynomialMap[exponent]!!.xor(value.value)
        } else {
            polynomialMap[exponent] = value.value
        }
    }
    return polynomialMap.toPolynomial()
}

fun Map<Int, Int>.toPolynomial(): Polynomial {
    return Polynomial(this.map { (exp, coef) -> exp to PolynomialCoefficient(coef) }.toMap())
}

fun Map.Entry<Int, PolynomialCoefficient>.multiply(other: Map.Entry<Int, PolynomialCoefficient>):
        Pair<Int, PolynomialCoefficient> =
    key + other.key to PolynomialCoefficient.ofAlpha(value.alphaExponent + other.value.alphaExponent % 255)
