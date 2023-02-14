package sternbauer.eu

fun String.indentEachLine(depth: Int = 2, symbol: String = " ") =
    this.split("\n").joinToString("\n") { symbol.repeat(depth) + it }

fun String.text() = Text(this)
fun String.p() = Paragraph(this)
fun String.h(level: Int) = Heading(level, this)
fun String.h1() = Heading(text = this)
fun String.h2() = Heading(2, this)
fun String.h3() = Heading(3, this)
fun String.h4() = Heading(4, this)
fun String.h5() = Heading(5, this)
fun String.h6() = Heading(6, this)