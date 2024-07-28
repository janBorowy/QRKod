import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.student.group
import kotlin.test.assertEquals

internal class GroupingSpec {

    @Test
    fun testGroupString() {
        assertEquals("".group(1), listOf())
        assertEquals("123".group(1), listOf("1", "2", "3"))
        assertEquals("123456".group(3), listOf("123", "456"))
        assertEquals("1234567".group(3), listOf("123", "456", "7"))
        assertThrows<IllegalArgumentException> { "123".group(0) }
        assertThrows<IllegalArgumentException> { "123".group(-1) }
    }
}