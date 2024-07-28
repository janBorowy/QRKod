package pl.student

private const val NOT_ENOUGH_SPACE_TO_ENCODE_BINARY_MESSAGE = "Not enough space to encode binary"

internal fun Int.toBinary(): Bits {
    val bits = Bits()
    var currentValue = this
    while (currentValue != 0) {
        bits.add(Bit.of(currentValue % 2))
        currentValue /= 2
    }
    bits.reverse()
    return bits
}

internal fun Int.toBinary(size: Int): Bits {
    val binary = toBinary()
    require (size >= binary.size) { NOT_ENOUGH_SPACE_TO_ENCODE_BINARY_MESSAGE }
    val result = Bits.zeroes(size)
    result.or(binary)
    return result
}