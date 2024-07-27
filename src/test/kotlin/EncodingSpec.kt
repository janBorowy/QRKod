import org.junit.jupiter.api.Test
import pl.student.*
import pl.student.AlphanumericEncoder
import pl.student.NumericEncoder
import kotlin.test.assertEquals


internal class EncodingSpec {

    @Test
    fun shouldEncodeNumericProperly() {
        assertEquals(Bits.concatonated(
            Bits.of("0001"),
            Bits.of("0000001000"),
            Bits.of("0000001100"),
            Bits.of("0101011001"),
            Bits.of("1000011")
        ), NumericEncoder.encode("01234567", version4))

        assertEquals(Bits.concatonated(
            Bits.of("0001"),
            Bits.of("0000000000")
        ), NumericEncoder.encode("", version4))

        assertEquals(Bits.concatonated(
            Bits.of("0001"),
            Bits.of("0000001001"),
            Bits.of("0001111011"),
            Bits.of("0111001000"),
            Bits.of("1100010101")
        ), NumericEncoder.encode("123456789", version4))
    }

    @Test
    fun shouldEncodeAlphanumericProperly() {
        assertEquals(Bits.concatonated(
            Bits.of("0010"),
            Bits.of("000000101"),
            Bits.of("00111001110"),
            Bits.of("11100111001"),
            Bits.of("000010")
        ), AlphanumericEncoder.encode("AC-42", version4))

        assertEquals(Bits.concatonated(
            Bits.of("0010"),
            Bits.of("000000110"),
            Bits.of("00111001110"),
            Bits.of("11100111001"),
            Bits.of("00001011011")
        ), AlphanumericEncoder.encode("AC-421", version4))

        assertEquals(Bits.concatonated(
            Bits.of("0010"),
            Bits.of("000000000")
        ), AlphanumericEncoder.encode("", version4))
    }

    @Test
    fun shouldEncodeBinaryProperly() {
        assertEquals(Bits.concatonated(
            Bits.of("0100"),
            Bits.of("00000101"),
            Bits.of("01000001"),
            Bits.of("01000011"),
            Bits.of("00101101"),
            Bits.of("00110100"),
            Bits.of("00110010")
        ), BinaryEncoder.encode("AC-42", version4))

        assertEquals(Bits.concatonated(
            Bits.of("0100"),
            Bits.of("00000000"),
        ), BinaryEncoder.encode("", version4))
    }
}