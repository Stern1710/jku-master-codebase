package fp

import java.io.BufferedReader
import java.io.File
import java.io.FileReader


fun String.ext() {
    println(this.length)
}

fun withRcvr(s: String, lbdaWithRcvr: String.() -> Unit) {
    s.lbdaWithRcvr()
}

fun <T, R> using(t : T, fn: T.() -> R) : R {
    return t.fn()
}

fun <T> T.build(fn: T.() -> Unit) : T {
    this.fn()
    return this
}

fun main() {

    val s : String = "ABC"

    s.ext()

    withRcvr(s) {
        println(this.length)
    }

    val text =
        using(StringBuilder()) {
            append("!")
            append("Kotlin ")
            append("Hello ")
            reverse()
            toString()
        }

    val text2 =
        StringBuilder().build() {
            append("!")
            append("Kotlin ")
            append("Hello ")
            reverse()

        } .toString()

    val file : java.io.File = File("text.txt")
    val reader : java.io.FileReader = FileReader("text.txt")
    using(reader) {
        var line = readLine()
        while (line != null) {
            println(line)
            line = readLine()
        }
    }

    val l =
        mutableListOf<Int>().build {
            add(2)
            add(3)
            add(4)
            reverse()
        }
    println(l)

    val multiLine =
        with(StringBuilder()) {
            appendLine("First line")
            append("2nd ")
            append("line")
            toString()
        }

    println(multiLine)


}

