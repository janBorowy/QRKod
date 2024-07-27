import org.junit.jupiter.api.Test
import pl.student.Bits
import pl.student.NumericEncoder
import pl.student.version4
import kotlin.test.assertEquals


class EncodingSpec {

    @Test
    fun shouldEncodeProperly() {
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
}