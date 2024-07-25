package pl.student

import java.util.BitSet

enum class InputMode() {
    NUMERIC(),
    ALPHANUMERIC(),
    BINARY();
//    KANJI(0b1000);

}

enum class ErrorCorrectionLevel {
    L, M, Q, H
}