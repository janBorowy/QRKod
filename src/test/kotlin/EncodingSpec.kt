import org.junit.jupiter.api.Test
import pl.student.Bits
import pl.student.version4
import pl.student.ErrorCorrectionLevel
import pl.student.BinaryEncoder
import pl.student.AlphanumericEncoder
import pl.student.NumericEncoder
import kotlin.test.assertEquals

const val FIRST_ALTERNATE = "11101100"
const val SECOND_ALTERNATE = "00010001"

internal class EncodingSpec {

    @Test
    fun shouldEncodeNumericProperly() {
        val encoder = NumericEncoder(version4, ErrorCorrectionLevel.H)
        assertEquals(Bits.concatonated(
            Bits.of("0001"),
            Bits.of("0000001000"),
            Bits.of("0000001100"),
            Bits.of("0101011001"),
            Bits.of("1000011"),
            Bits.of("0000"),
            Bits.of("000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(15))
        ), encoder.encode("01234567"))

        assertEquals(Bits.concatonated(
            Bits.of("0001"),
            Bits.of("0000000000"),
            Bits.of("0000"),
            Bits.of("000000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(16)),
            Bits.of(FIRST_ALTERNATE)
        ), encoder.encode("",))

        assertEquals(Bits.concatonated(
            Bits.of("0001"),
            Bits.of("0000001001"),
            Bits.of("0001111011"),
            Bits.of("0111001000"),
            Bits.of("1100010101"),
            Bits.of("0000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(15))
        ), encoder.encode("123456789"))
    }

    @Test
    fun shouldEncodeAlphanumericProperly() {
        val encoder = AlphanumericEncoder(version4, ErrorCorrectionLevel.H)
        assertEquals(Bits.concatonated(
            Bits.of("0010"),
            Bits.of("000000101"),
            Bits.of("00111001110"),
            Bits.of("11100111001"),
            Bits.of("000010"),
            Bits.of("0000"),
            Bits.of("000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(15))
        ), encoder.encode("AC-42"))

        assertEquals(Bits.concatonated(
            Bits.of("0010"),
            Bits.of("000000110"),
            Bits.of("00111001110"),
            Bits.of("11100111001"),
            Bits.of("00001011011"),
            Bits.of("0000"),
            Bits.of("000000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(14)),
            Bits.of(FIRST_ALTERNATE)
        ), encoder.encode("AC-421"))

        assertEquals(Bits.concatonated(
            Bits.of("0010"),
            Bits.of("000000000"),
            Bits.of("0000"),
            Bits.of("0000000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(16)),
            Bits.of(FIRST_ALTERNATE)
        ), encoder.encode(""))
    }

    @Test
    fun shouldEncodeBinaryProperly() {
        val encoder = BinaryEncoder(version4, ErrorCorrectionLevel.H)
        assertEquals(Bits.concatonated(
            Bits.of("0100"),
            Bits.of("00000101"),
            Bits.of("01000001"),
            Bits.of("01000011"),
            Bits.of("00101101"),
            Bits.of("00110100"),
            Bits.of("00110010"),
            Bits.of("0000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(14)),
            Bits.of(FIRST_ALTERNATE)
        ), encoder.encode("AC-42"))

        assertEquals(Bits.concatonated(
            Bits.of("0100"),
            Bits.of("00000000"),
            Bits.of("0000"),
            Bits.of((FIRST_ALTERNATE + SECOND_ALTERNATE).repeat(17))
        ), encoder.encode(""))
    }
}