package sternbauer.eu

/*
Go Strings! Go Chars!
(Go Banana! https://www.youtube.com/watch?v=7m1h0Hf5uMs)
 */
fun main() {
    val lorem = "Lorem ipsum dolor sit amet. Consetetur sadipscing elitr, Ced diam nonumy eirmod tempor lorem ut labore"

    //1
    val chunks = lorem.chunked(5).forEach { print("$it;") }
    println("")

    //2
    println(lorem.drop(6))
    println(lorem.dropLast(8))
    println(lorem.dropLastWhile { c -> c !in 'A'..'Z' }) //Drops everything after the last capital letter

    //3
    println(lorem.filter { it !in 'A'..'Z' }) //removes all capital letters, not with isLowerCase function this time

    //4
    println(lorem.replace("Lorem", "Lol", true))

    //5
    println(lorem.reversed())

    //6
    println(lorem.replaceFirstChar { if (it.isLowerCase()) it.uppercaseChar() else it }) //Replaces first char iff capital letter

    //7
    println("Lower case letters: ${lorem.count { it in 'a'..'z' }}")
    println("Lower case letters: ${lorem.count { it in 'A'..'Z' }}")

    //8
    println(lorem.firstOrNull { it == 'x' }) //No x --> null
    println(lorem.firstOrNull { it.isLowerCase() }) //First lowercase letter, it is an o from Lorem

    //9
    println("Element at 30: ${lorem.elementAtOrElse(30) { '-' }}")
    println("Element at 1000: ${lorem.elementAtOrElse(1000) { '-' }}")

    //10
    lorem.split(" ").forEach { if (it.endsWith('t')) println("$it ends with t") }
}