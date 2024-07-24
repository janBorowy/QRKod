import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.student.InputMode.ALPHANUMERIC
import pl.student.InputMode.BINARY
import pl.student.InputMode.NUMERIC
import pl.student.QRParsingException
import pl.student.isAlphanumeric
import pl.student.isBinary
import pl.student.isNumeric
import pl.student.testInputMode
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


internal class QREnumsTestUtils {

    @Test
    fun testIsNumericMode() {
        assertTrue { isNumeric("") }
        assertTrue { isNumeric("123") }
        assertTrue { isNumeric("0") }
        assertTrue { isNumeric("9") }
        assertFalse { isNumeric(" ") }
        assertFalse { isNumeric("abc") }
        assertFalse { isNumeric("9a") }
    }

    @Test
    fun testIsAlphanumeric() {
        assertTrue { isAlphanumeric("") }
        assertTrue { isAlphanumeric(" ") }
        assertTrue { isAlphanumeric("123") }
        assertTrue { isAlphanumeric("ABC") }
        assertFalse { isAlphanumeric("abc") }
        assertTrue { isAlphanumeric("AXYZ") }
        assertTrue { isAlphanumeric("1AB2C\$%*+-./: ") }
    }

    @Test
    fun testIsBinary() {
        assertTrue { isBinary("") }
        assertTrue { isBinary(" ") }
        assertTrue { isBinary("123") }
        assertTrue { isBinary("ABC") }
        assertTrue { isBinary("abc") }
        assertTrue { isBinary("AXYZ") }
        assertTrue { isBinary("1AB2C\$%*+-./: ") }
    }

    @Test
    fun testTestInputMode() {
        assertEquals(testInputMode(""), NUMERIC)
        assertEquals(testInputMode("123"), NUMERIC)
        assertEquals(testInputMode(" "), ALPHANUMERIC)
        assertEquals(testInputMode("ABC"), ALPHANUMERIC)
        assertEquals(testInputMode("abc"), BINARY)
        assertThrows<QRParsingException> {
            testInputMode("漢字")
        }
    }
}