import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.student.groupString
import kotlin.test.assertEquals

class GroupingSpec {

    @Test
    fun testGroupString() {
        assertEquals(groupString("", 1), listOf())
        assertEquals(groupString("123", 1), listOf("1", "2", "3"))
        assertEquals(groupString("123456", 3), listOf("123", "456"))
        assertEquals(groupString("1234567", 3), listOf("123", "456", "7"))
        assertThrows<IllegalArgumentException> { groupString("123", 0) }
        assertThrows<IllegalArgumentException> { groupString("123", -1) }
    }
}