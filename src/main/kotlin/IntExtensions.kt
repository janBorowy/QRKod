package pl.student

fun Int.toBinary(): Bits {
    val bits = Bits()
    var currentValue = this
    while (currentValue != 0) {
        bits.add(Bit.of(currentValue % 2))
        currentValue /= 2
    }
    bits.reverse()
    return bits
}