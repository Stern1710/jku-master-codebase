package fp

import java.util.*
import java.util.stream.Collectors

fun main() {

    val numbers = listOf<Int>(1, 3, 5, 7, 2, 5, 9, 13, 1, 3, 5, 7, 2, 5, 9, 13, 3, 5, 7, 2, 5, 9, 13, 1, 3, 5, 7, 2, 5, 9, 13)

    val twoOddSquares : List<Int> =
        numbers.map {i -> i * i }
            .filter { sq -> sq % 2 != 0 }
            .take(2)

    val twoOddSquares2 : List<Int> =
        numbers.asSequence()
            .map {i -> i * i }
            .filter { sq -> sq % 2 != 0 }
            .take(2)
            .toList()

    // generated infinite
    val infinite : Sequence<Int> = generateSequence (0) { x -> x + 1 }
    infinite.takeWhile { it < 100 } .forEach { println(it) }

    // infinite random number stream
    val RAND : Random = Random()
    val rands = generateSequence { RAND.nextInt(100) }
    rands.take(19).forEach { println(it) }

    // using Java Streams
    val result : List<Int> = numbers.stream().map { it * it } .filter { it > 10 } .limit(2).collect(Collectors.toList())

}