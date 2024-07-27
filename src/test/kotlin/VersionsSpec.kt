import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.student.determineVersionToEncodeIn
import kotlin.test.assertEquals

internal class VersionsSpec {

    @Test
    fun testDetermineVersionToEncodeIn() {
        assertEquals(determineVersionToEncodeIn(21), 1)
        assertEquals(determineVersionToEncodeIn(22), 2)
        assertEquals(determineVersionToEncodeIn(25), 2)
        assertEquals(determineVersionToEncodeIn(26), 3)
        assertEquals(determineVersionToEncodeIn(177), 40)
        assertThrows<IllegalArgumentException> { determineVersionToEncodeIn(-1) }
        assertThrows<IllegalArgumentException> { determineVersionToEncodeIn(178) }
    }

}