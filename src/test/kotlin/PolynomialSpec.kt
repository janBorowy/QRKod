import org.junit.jupiter.api.Test
import pl.student.*
import kotlin.test.assertEquals

internal class PolynomialSpec {

    @Test
    fun shouldConvertToMessagePolynomial() {
        assertEquals(
            Polynomial(
                mapOf(
                    3 to PolynomialCoefficient(69),
                    2 to PolynomialCoefficient(242),
                    1 to PolynomialCoefficient(17),
                    0 to PolynomialCoefficient(171)
                )
            ),
            convertToMessagePolynomial(
                Codewords.fromDataBits(
                    Bits.concatonated(
                        Bits.of("01000101"),
                        Bits.of("11110010"),
                        Bits.of("00010001"),
                        Bits.of("10101011")
                    )
                )
            )
        )
    }

    @Test
    fun shouldMultiplyPolynomials() {
        val first = Polynomial(
            mapOf(
                2 to PolynomialCoefficient(1),
                1 to PolynomialCoefficient(3),
                0 to PolynomialCoefficient(2)
            )
        )
        val second = Polynomial(
            mapOf(
                1 to PolynomialCoefficient(1),
                0 to PolynomialCoefficient(4)
            )
        )
        assertEquals(
            Polynomial(
                mapOf(
                    3 to PolynomialCoefficient(1),
                    2 to PolynomialCoefficient(7),
                    1 to PolynomialCoefficient(14),
                    0 to PolynomialCoefficient(8)
                )
            ),
            first.multiply(second)
        )
    }

    @Test
    fun shouldCalculateGeneratorPolynomial() {
        assertEquals(
            Polynomial(
                mapOf(
                    2 to PolynomialCoefficient(1),
                    1 to PolynomialCoefficient.ofAlpha(25),
                    0 to PolynomialCoefficient.ofAlpha(1)
                )
            ), calculateGeneratorPolynomial(2)
        )

        assertEquals(
            Polynomial(
                mapOf(
                    5 to PolynomialCoefficient(1),
                    4 to PolynomialCoefficient.ofAlpha(113),
                    3 to PolynomialCoefficient.ofAlpha(164),
                    2 to PolynomialCoefficient.ofAlpha(166),
                    1 to PolynomialCoefficient.ofAlpha(119),
                    0 to PolynomialCoefficient.ofAlpha(10)
                )
            ), calculateGeneratorPolynomial(5)
        )
    }
}