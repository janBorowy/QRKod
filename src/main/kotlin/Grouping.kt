package pl.student

import kotlin.math.min

fun String.groupString(groupSize: Int): List<String> {
    require(groupSize > 0) { "Group size must be a positive integer" }
    var currentGroupIndex = 0
    val output: MutableList<String> = mutableListOf()
    while (currentGroupIndex * groupSize < length) {
        val startIndex = currentGroupIndex * groupSize
        val endIndex = min(startIndex + groupSize, length)
        output.add(substring(startIndex, endIndex))
        ++currentGroupIndex
    }
    return output
}