package sternbauer.eu.aoc

import java.io.File

/*
    AOC1 utilizes
    * Lambdas (at leas I hope I got it right), example for number of increases in sliding windows,
        mapping of String to Int where both times I use the "it" operator
    * data class for the sliding window
    * Extension method for File to automatically transform the read-in elements to Sliding Window data classes
    * Override the compareTo operator for Sliding Window, to easily compare two sliding windows with each other

    Additionally, I found a weird behaviour around Int vs. Long, see main function for that. Only cost me half an hour
 */

const val fileAOC1 = "./data/aoc1.txt"

data class SlidWin(val e1: Int, val e2: Int, val e3: Int) {
    operator fun compareTo(other: SlidWin) = this.sum.compareTo(other.sum)

    private val sum: Int
        get() = e1 + e2 + e3
}

/* Extension method */
fun File.readWindows(): List<SlidWin> {
    val elems = readLines().map { s -> Integer.valueOf(s) }
    if (elems.size < 3) return listOf() //Just return an empty list, as there are insufficient elements for a window

    return (2 until elems.size).map { SlidWin(elems[it - 2], elems[it - 1], elems[it]) }.toList()
}

fun main() {
    val windows = File(fileAOC1).readWindows()
    /*
    I know that the IDE warns about a useless cast, BUT if you remove it, Kotlin cannot differ between Int and Long
    See more in this at: https://youtrack.jetbrains.com/issue/KT-46360
    Kotlin: Overload resolution ambiguity:
    public inline fun <T> Iterable<TypeVariable(T)>.sumOf(selector: (TypeVariable(T)) -> Int): Int defined in kotlin.collections
    public inline fun <T> Iterable<TypeVariable(T)>.sumOf(selector: (TypeVariable(T)) -> Long): Long defined in kotlin.collections
    */
    val c1 = (1 until windows.size).sumOf { if (windows[it] > windows[it - 1]) 1 as Int else 0 }

    /*
    However, once you extract the lambda and pass it as a variable, it just works out. No ambiguity about the 0/1
    They produce the same result btw :)
     */
    val lm = { index: Int -> if (windows[index] > windows[index - 1]) 1 else 0 }
    val c2 = (1 until windows.size).sumOf(lm)

    println("$c1 sums of our sliding window were greater than the last one")
    println("The second calculated sum IS ${if (c1 != c2) "NOT " else ""}equal to the first sum")
}