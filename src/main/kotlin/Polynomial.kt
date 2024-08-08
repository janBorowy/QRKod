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
        sortedMapOf(
            1 to PolynomialCoefficient.ofAlpha(0),
            0 to PolynomialCoefficient.ofAlpha(0)
        )
    )
    for (i in 2..n) {
        val toMultiplyBy = Polynomial(
            sortedMapOf(
                1 to PolynomialCoefficient.ofAlpha(0),
                0 to PolynomialCoefficient.ofAlpha(i - 1)
            )
        )
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

fun Polynomial.reminderAfterDivisionFor(other: Polynomial, n: Int): Polynomial {
    var currentDividend = this
    for (i in 0..<n) {
        val divisor = other.multiply(Polynomial(sortedMapOf(currentDividend.leadTerm.divide(other.leadTerm))))
        currentDividend = currentDividend.xor(divisor)
        currentDividend.exponentToCoefficient.keys.forEach { currentDividend.exponentToCoefficient.remove(it, PolynomialCoefficient(0))}
    }
    return currentDividend
}

fun Polynomial.xor(other: Polynomial): Polynomial {
    val result = Polynomial(exponentToCoefficient.map { (exponent, coef) ->
        if (exponent in other.exponentToCoefficient) {
            exponent to PolynomialCoefficient(other.exponentToCoefficient[exponent]!!.value.xor(coef.value))
        } else {
            exponent to coef
        }
    }.toMap().toSortedMap())
    other.exponentToCoefficient.forEach { (exponent, coef) ->
        if (exponent !in result.exponentToCoefficient) {
            result.exponentToCoefficient[exponent] = coef
        }
    }
    return result
}

fun Map<Int, Int>.toPolynomial(): Polynomial {
    return Polynomial(this.map { (exp, coef) -> exp to PolynomialCoefficient(coef) }.toMap().toSortedMap())
}

fun Map.Entry<Int, PolynomialCoefficient>.multiply(other: Map.Entry<Int, PolynomialCoefficient>):
        Pair<Int, PolynomialCoefficient> =
    key + other.key to PolynomialCoefficient.ofAlpha((value.alphaExponent + other.value.alphaExponent) % 255)

fun Map.Entry<Int, PolynomialCoefficient>.divide(other: Map.Entry<Int, PolynomialCoefficient>):
        Pair<Int, PolynomialCoefficient> =
    key - other.key to PolynomialCoefficient(value.value / other.value.value)