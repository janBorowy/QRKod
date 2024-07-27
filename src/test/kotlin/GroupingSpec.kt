import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.student.groupString
import kotlin.test.assertEquals

internal class GroupingSpec {

    @Test
    fun testGroupString() {
        assertEquals("".groupString(1), listOf())
        assertEquals("123".groupString(1), listOf("1", "2", "3"))
        assertEquals("123456".groupString(3), listOf("123", "456"))
        assertEquals("1234567".groupString(3), listOf("123", "456", "7"))
        assertThrows<IllegalArgumentException> { "123".groupString(0) }
        assertThrows<IllegalArgumentException> { "123".groupString(-1) }
    }
}