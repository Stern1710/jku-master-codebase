val loader: String by lazy {
    println("I am being loaded!")
    "This is me!"
}

val thrice : (String) -> Unit = {
    s -> (1..3).forEach { _ -> println(s)}
}

inline fun doThrice (f: () -> Unit) {
    (1..3).forEach { _ -> f() }
}

object MyUtils {
    fun firstWord(s: String) = s.split(" ").first()
    fun lastWord(s: String) = s.split(" ").last()
}

fun main() {
    doThrice { println(loader) }
    thrice(loader)
    val s = "Das ist das Haus vom Nikolaus"
    println(MyUtils.firstWord(s))
    println(MyUtils.lastWord(s))
}