package pl.student

import kotlin.math.min

fun groupString(input: String, groupSize: Int): List<String> {
    require(groupSize > 0) { "Group size must be a positive integer" }
    var currentGroupIndex = 0
    val output: MutableList<String> = mutableListOf()
    while (currentGroupIndex * groupSize < input.length) {
        val startIndex = currentGroupIndex * groupSize
        val endIndex = min(startIndex + groupSize, input.length)
        output.add(input.substring(startIndex, endIndex))
        ++currentGroupIndex
    }
    return output
}