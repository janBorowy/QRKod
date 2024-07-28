package pl.student

const val CODEWORD_SIZE = 8

data class Codeword(val bits: Bits) {

    init {
        require(bits.size == CODEWORD_SIZE) { "Failed to instantiate codeword of size different than 8" }
    }

    companion object {
        fun of(bits: Bits): Codeword {
            return Codeword(bits)
        }
    }

}

data class Codewords(val codewords: MutableList<Codeword>): MutableList<Codeword> by codewords {

    companion object {
        fun fromDataBits(bits: Bits): Codewords {
            return Codewords(bits.group(CODEWORD_SIZE).map { Codeword.of(Bits(it.toMutableList())) }.toMutableList())
        }
    }
}