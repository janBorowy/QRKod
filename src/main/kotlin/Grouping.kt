package pl.student

import kotlin.math.min

fun <T> Collection<T>.group(groupSize: Int): List<Collection<T>> {
    require(groupSize > 0) { "Group size must be a positive integer" }
    return windowed(groupSize, groupSize, partialWindows = true)
}

fun CharSequence.group(groupSize: Int): List<String> {
    require(groupSize > 0) { "Group size must be a positive integer" }
    return windowed(groupSize, groupSize, partialWindows = true)
}
