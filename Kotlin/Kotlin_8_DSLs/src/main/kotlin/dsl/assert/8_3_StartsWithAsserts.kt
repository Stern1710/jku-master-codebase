package dsl.assert

interface Matcher<in T> {
    fun test(value: T) : Boolean
}

infix fun <T> T.should(matcher: Matcher<T>) : Boolean = matcher.test(this)

class startWith(val prefix: String) : Matcher<String> {

    override fun test(value: String): Boolean {
        if (! value.startsWith(prefix)) {
            throw AssertionError("String $value does not start with $prefix")
        } else {
            println("OK: String $value starts with $prefix\"")
            return true
        }
    }

}

object start  // implementing keyword start as object

infix fun String.should(x : start) : StartWrapper = StartWrapper(this)  // returning wrapper with more functions

class StartWrapper(val value: String) {
    infix fun with(prefix: String) {
        if (! value.startsWith(prefix)) {
            throw AssertionError("String $value does not start with $prefix")
        }
    }
}

fun main() {

    val word = "kotlin"
    word should startWith("kott")
    word should start with "kot"

}

