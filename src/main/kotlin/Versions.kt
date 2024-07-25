package pl.student

import pl.student.ErrorCorrectionLevel.H
import pl.student.ErrorCorrectionLevel.L
import pl.student.ErrorCorrectionLevel.M
import pl.student.ErrorCorrectionLevel.Q
import pl.student.InputMode.ALPHANUMERIC
import pl.student.InputMode.BINARY
import pl.student.InputMode.NUMERIC

fun determineVersionToEncodeIn(size: Int): Int {
    require(size in 0..177) { "Size should be in range 0..177" }
    return if (size - 21 <= 0) 1 else ((size - 22) / 4) + 2
}

data class Version(
    val index: Int,
    val numberOfCodewords: Map<ErrorCorrectionLevel, Int>,
    val numberOfDataBits: Map<ErrorCorrectionLevel, Int>,
    val numberOfCountBits: (InputMode) -> Int,
    val dataCapacity: Map<ErrorCorrectionLevel, (InputMode) -> Int>
)

val version4 = Version(index = 4,
    numberOfCodewords = mapOf(
        L to 80,
        M to 64,
        Q to 48,
        H to 36
    ),
    numberOfDataBits = mapOf(
        L to 640,
        M to 512,
        Q to 384,
        H to 288
    ),
    numberOfCountBits = {
        when (it) {
            NUMERIC -> 10
            ALPHANUMERIC -> 9
            BINARY -> 8
        }
    },
    dataCapacity = mapOf(
        L to { mode ->
            when (mode) {
                NUMERIC -> 187
                ALPHANUMERIC -> 114
                BINARY -> 78
            }
        },
        M to { mode ->
            when (mode) {
                NUMERIC -> 149
                ALPHANUMERIC -> 90
                BINARY -> 62
            }
        },
        Q to { mode ->
            when (mode) {
                NUMERIC -> 111
                ALPHANUMERIC -> 67
                BINARY -> 46
            }
        },
        H to { mode ->
            when (mode) {
                NUMERIC -> 82
                ALPHANUMERIC -> 50
                BINARY -> 34
            }
        }
    )
)
