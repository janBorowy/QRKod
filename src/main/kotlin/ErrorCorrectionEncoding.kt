package pl.student

class ErrorCorrectionCodewordsEncoder(val version: Version, val errorCorrectionLevel: ErrorCorrectionLevel) {

    fun encode(dataCodewords: Codewords): Codewords {
        val numberOfErrorCorrectionCodewords = version.numberOfErrorCorrectionCodewords(errorCorrectionLevel)
        val numberOfDataCodewords = version.numberOfCodewords(errorCorrectionLevel) - numberOfErrorCorrectionCodewords
        val messagePolynomial = convertToMessagePolynomial(dataCodewords)
        val generatorPolynomial =
            calculateGeneratorPolynomial(numberOfErrorCorrectionCodewords)
        val divisionResult = messagePolynomial.multiply(Polynomial.ofSingleExponent(numberOfErrorCorrectionCodewords))
            .reminderAfterDivisionFor(
                generatorPolynomial.multiply(Polynomial.ofSingleExponent(numberOfDataCodewords)),
                numberOfDataCodewords
            )
        return codewordsFromPolynomialCoefficients(divisionResult)
    }

    private fun codewordsFromPolynomialCoefficients(polynomial: Polynomial): Codewords =
        Codewords.fromDataBits(
            polynomial.exponentToCoefficient.values.map { coef ->
                coef.value.toBinary()
            }.flatten().toMutableList().toBits()
        )
}

