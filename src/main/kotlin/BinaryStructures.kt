package pl.student

import java.nio.ByteBuffer
import kotlin.math.min

data class Bit(val on: Boolean) {

    companion object {
        fun of(on: Int): Bit {
            return Bit(on != 0)
        }
    }
}

data class Bits(val bits: MutableList<Bit>) : MutableList<Bit> by bits {

    constructor() : this(mutableListOf())

    companion object {
        fun zeroes(size: Int): Bits {
            return Bits(BooleanArray(size).map { Bit(false) }.toMutableList())
        }

        fun of(string: String): Bits {
            return of(*string.map { it.digitToInt() }.toIntArray())
        }

        fun of(vararg int: Int): Bits {
            return Bits(int.map {Bit(it != 0)}.toMutableList())
        }

        fun concatonated(vararg bitGroups: Bits): Bits {
            return Bits(bitGroups.flatMap { it }.toMutableList())
        }
    }

    fun or(anotherList: Bits) {
        return orSublist(anotherList, this.size - anotherList.size)
    }

    fun orSublist(anotherList: Bits, from: Int = 0) {
        for (i in from until from + anotherList.size) {
            if (this[i].on || anotherList[i - from].on)
                this[i] = Bit(true)
        }
    }

    override fun toString(): String {
        return bits.joinToString(separator = "") { if (it.on) "1" else "0"  }
    }
}

internal fun bitsOfByteBuffer(buffer: ByteBuffer): Bits {
    val onList: MutableList<Boolean> = mutableListOf()
    while (buffer.hasRemaining()) {
        val byte: Int = buffer.get().toInt()
        for (shift in 7 downTo 0) {
            onList.add(byte.shr(shift).takeLowestOneBit() == 1)
        }
    }
    return Bits(onList.map { Bit(it) }.toMutableList())
}

