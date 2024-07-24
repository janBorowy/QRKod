package pl.student

import pl.student.InputMode.ALPHANUMERIC
import pl.student.InputMode.BINARY
import pl.student.InputMode.NUMERIC

fun testInputMode(input: String): InputMode =
    when {
        isNumeric(input) -> NUMERIC
        isAlphanumeric(input) -> ALPHANUMERIC
        isBinary(input) -> BINARY
        else -> { throw QRParsingException("Could not determine encoding data")}
    }

fun isNumeric(input: String): Boolean =
    input.all { it in '0'..'9' }

private val ALPHANUMERIC_SYMBOLS: Set<Char> = setOf('$', '%', '*', '+', '-', '.', '/', ':', ' ')

fun isAlphanumeric(input: String): Boolean =
    input.all {
        it in '0'..'9' ||
        it in 'A'..'Z' ||
        it in ALPHANUMERIC_SYMBOLS
    }

fun isBinary(input: String): Boolean = Charsets.ISO_8859_1.newEncoder().canEncode(input)
