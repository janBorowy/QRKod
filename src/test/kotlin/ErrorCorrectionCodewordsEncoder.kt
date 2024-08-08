import org.junit.jupiter.api.Test
import pl.student.Bits
import pl.student.Codewords
import pl.student.ErrorCorrectionCodewordsEncoder
import pl.student.ErrorCorrectionLevel
import kotlin.test.assertEquals

internal class ErrorCorrectionCodewordsEncoderSpec {

    @Test
    fun shouldEncodeErrorCorrectionCodewordsCorrectly() {
        val encoder = ErrorCorrectionCodewordsEncoder(version1, ErrorCorrectionLevel.M)
        assertEquals(
            Codewords.fromDataBits(
                Bits.concatonated(
                    Bits.of("11000100"),
                    Bits.of("00100011"),
                    Bits.of("00100111"),
                    Bits.of("01110111"),
                    Bits.of("11101011"),
                    Bits.of("11010111"),
                    Bits.of("11100111"),
                    Bits.of("11100010"),
                    Bits.of("01011101"),
                    Bits.of("00010111"),
                )
            ),
            Codewords.fromDataBits(
                Bits.concatonated(
                    Bits.of("00100000"),
                    Bits.of("01011011"),
                    Bits.of("00001011"),
                    Bits.of("01111000"),
                    Bits.of("11010001"),
                    Bits.of("01110010"),
                    Bits.of("11011100"),
                    Bits.of("01001101"),
                    Bits.of("01000011"),
                    Bits.of("01000000"),
                    Bits.of("11101100"),
                    Bits.of("00010001"),
                    Bits.of("11101100"),
                    Bits.of("00010001"),
                    Bits.of("11101100"),
                    Bits.of("00010001")
                )
            )
        )
    }
}