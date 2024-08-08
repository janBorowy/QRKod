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
    val numberOfCodewords: (ErrorCorrectionLevel) -> Int,
    val numberOfDataBits: (ErrorCorrectionLevel) -> Int,
    val numberOfCountBits: (InputMode) -> Int,
    val numberOfErrorCorrectionCodewords: (ErrorCorrectionLevel) -> Int,
    val dataCapacity: (ErrorCorrectionLevel) -> (InputMode) -> Int
)

val version1 = Version(
    index = 1,
    numberOfCodewords =
)

val version4 = Version(
    index = 4,
    numberOfCodewords = { errorCorrectionLevel ->
        when (errorCorrectionLevel) {
            L -> 80
            M -> 64
            Q -> 48
            H -> 36
        }
    },
    numberOfDataBits = { errorCorrectionLevel ->
        when (errorCorrectionLevel) {
            L -> 640
            M -> 512
            Q -> 384
            H -> 288
        }
    },
    numberOfCountBits = {
        when (it) {
            NUMERIC -> 10
            ALPHANUMERIC -> 9
            BINARY -> 8
        }
    },
    numberOfErrorCorrectionCodewords = { errorCorrectionLevel ->
        when (errorCorrectionLevel) {
            L -> 20
            M -> 36
            Q -> 52
            H -> 64
        }
    },
    dataCapacity = { errorCorrectionLevel ->
        when (errorCorrectionLevel) {
            L -> { mode ->
                when (mode) {
                    NUMERIC -> 187
                    ALPHANUMERIC -> 114
                    BINARY -> 78
                }
            }

            M -> { mode ->
                when (mode) {
                    NUMERIC -> 149
                    ALPHANUMERIC -> 90
                    BINARY -> 62
                }
            }

            Q -> { mode ->
                when (mode) {
                    NUMERIC -> 111
                    ALPHANUMERIC -> 67
                    BINARY -> 46
                }
            }

            H -> { mode ->
                when (mode) {
                    NUMERIC -> 82
                    ALPHANUMERIC -> 50
                    BINARY -> 34
                }
            }
        }
    }
)
