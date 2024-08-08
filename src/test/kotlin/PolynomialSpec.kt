import org.junit.jupiter.api.Test
import pl.student.*
import kotlin.test.assertEquals

internal class PolynomialSpec {

    @Test
    fun shouldConvertToMessagePolynomial() {
        assertEquals(
            Polynomial(
                sortedMapOf(
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
            sortedMapOf(
                2 to PolynomialCoefficient(1),
                1 to PolynomialCoefficient(3),
                0 to PolynomialCoefficient(2)
            )
        )
        val second = Polynomial(
            sortedMapOf(
                1 to PolynomialCoefficient(1),
                0 to PolynomialCoefficient(4)
            )
        )
        assertEquals(
            Polynomial(
                sortedMapOf(
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
                sortedMapOf(
                    2 to PolynomialCoefficient(1),
                    1 to PolynomialCoefficient.ofAlpha(25),
                    0 to PolynomialCoefficient.ofAlpha(1)
                )
            ), calculateGeneratorPolynomial(2)
        )

        assertEquals(
            Polynomial(
                sortedMapOf(
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

    @Test
    fun shouldDividePolynomialsCorrectly() {
        assertEquals(
            Polynomial(
                sortedMapOf(
                    2 to PolynomialCoefficient(11),
                    1 to PolynomialCoefficient(5),
                    0 to PolynomialCoefficient(8)
                )
            ), Polynomial(sortedMapOf(
                3 to PolynomialCoefficient(4),
                2 to PolynomialCoefficient(3),
                1 to PolynomialCoefficient(5),
                0 to PolynomialCoefficient(8)
            )).reminderAfterDivisionFor(Polynomial(sortedMapOf(1 to PolynomialCoefficient(1), 0 to PolynomialCoefficient(2))), 1)
        )

        assertEquals(
            Polynomial(
                sortedMapOf(
                    1 to PolynomialCoefficient(19),
                    0 to PolynomialCoefficient(8)
                )
            ), Polynomial(sortedMapOf(
                3 to PolynomialCoefficient(4),
                2 to PolynomialCoefficient(3),
                1 to PolynomialCoefficient(5),
                0 to PolynomialCoefficient(8)
            )).reminderAfterDivisionFor(Polynomial(sortedMapOf(1 to PolynomialCoefficient(1), 0 to PolynomialCoefficient(2))), 2)
        )

        assertEquals(
            Polynomial(
                sortedMapOf(
                    0 to PolynomialCoefficient(46)
                )
            ), Polynomial(sortedMapOf(
                3 to PolynomialCoefficient(4),
                2 to PolynomialCoefficient(3),
                1 to PolynomialCoefficient(5),
                0 to PolynomialCoefficient(8)
            )).reminderAfterDivisionFor(Polynomial(sortedMapOf(1 to PolynomialCoefficient(1), 0 to PolynomialCoefficient(2))), 3)
        )

        assertEquals(
            Polynomial(
                sortedMapOf(
                    9 to PolynomialCoefficient(196),
                    8 to PolynomialCoefficient(35),
                    7 to PolynomialCoefficient(39),
                    6 to PolynomialCoefficient(119),
                    5 to PolynomialCoefficient(235),
                    4 to PolynomialCoefficient(215),
                    3 to PolynomialCoefficient(231),
                    2 to PolynomialCoefficient(226),
                    1 to PolynomialCoefficient(93),
                    0 to PolynomialCoefficient(23)
                )
            ), Polynomial(sortedMapOf(
                25 to PolynomialCoefficient(32),
                24 to PolynomialCoefficient(91),
                23 to PolynomialCoefficient(11),
                22 to PolynomialCoefficient(120),
                21 to PolynomialCoefficient(209),
                20 to PolynomialCoefficient(114),
                19 to PolynomialCoefficient(220),
                18 to PolynomialCoefficient(77),
                17 to PolynomialCoefficient(67),
                16 to PolynomialCoefficient(64),
                15 to PolynomialCoefficient(236),
                14 to PolynomialCoefficient(17),
                13 to PolynomialCoefficient(236),
                12 to PolynomialCoefficient(17),
                11 to PolynomialCoefficient(236),
                10 to PolynomialCoefficient(17)
            )).reminderAfterDivisionFor(
                Polynomial(sortedMapOf(
                    25 to PolynomialCoefficient.ofAlpha(0),
                    24 to PolynomialCoefficient.ofAlpha(251),
                    23 to PolynomialCoefficient.ofAlpha(67),
                    22 to PolynomialCoefficient.ofAlpha(46),
                    21 to PolynomialCoefficient.ofAlpha(61),
                    20 to PolynomialCoefficient.ofAlpha(118),
                    19 to PolynomialCoefficient.ofAlpha(70),
                    18 to PolynomialCoefficient.ofAlpha(64),
                    17 to PolynomialCoefficient.ofAlpha(94),
                    16 to PolynomialCoefficient.ofAlpha(32),
                    15 to PolynomialCoefficient.ofAlpha(45)
                )), 16
            )
        )
    }
}