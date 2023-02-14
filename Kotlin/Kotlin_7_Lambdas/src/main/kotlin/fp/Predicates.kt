package fp

import fp.FList

fun <E: Any> equalTo(other: Any?) : (E) -> Boolean = { x -> x.equals(other) }

fun <E: Any> notEqualTo(other: Any?) : (E) -> Boolean = { x -> ! x.equals(other) }

fun <E : Comparable<E>> greaterTo(other: E) : (E) -> Boolean = { x -> x > other}

fun <E : Number> toDoubleFn() : (E) -> Double = { x -> x.toDouble() }

fun <C : CharSequence> containsWhiteSpace() : (C) -> Boolean = {s -> s.contains(" ")}

fun <C : CharSequence> startsWith(start : CharSequence) : (C) -> Boolean = {s -> s.startsWith(start) }


fun main() {

}