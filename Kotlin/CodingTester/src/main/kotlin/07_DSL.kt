import java.lang.AssertionError

sealed class testWords
object start : testWords()
object end : testWords()

infix fun String.should(x: start) = StartTest(this)
infix fun String.should(x: end) = EndTest(this)

class StartTest(private val elem: String) {
    infix fun with (tester: String) : Boolean {
        if(!elem.startsWith(tester)) {
            throw AssertionError("'$elem' does not start with '$tester'")
        }
        println("With start test successful")
        return true
    }
}

class EndTest(private val elem: String){
    infix fun with (tester: String) : Boolean {
        if(!elem.endsWith(tester)) {
            throw AssertionError("'$elem' does not end with '$tester'")
        }
        println("With end test successful")
        return true
    }
}

infix fun <T> T.test1(tester: T.() -> Boolean) {
    if (this.tester()) {
        println("Successful testing")
    } else {
        throw AssertionError("The test failed!")
    }

}

infix fun <T> T.test2(tester: (T) -> Boolean) {
    if (tester(this)) {
        println("Successful testing")
    } else {
        throw AssertionError("The test failed!")
    }
}

fun <T> test3(item: T, tester: T.() -> Boolean) {
    if (item.tester()) {
        println("Successful testing")
    } else {
        throw AssertionError("The test failed!")
    }
}

fun <T> test4(item: T, tester: (T) -> Boolean) {
    if (tester(item)) {
        println("Successful testing")
    } else {
        throw AssertionError("The test failed!")
    }
}

fun main() {
    val text = "This is a super duper string"

    text should start with "This"
    text should end with "string"

    text test1 {this.length > 10}
    text test2 {it.length > 10}

    test3(text) {
        this.length > 20
    }
    test4(text) {
        it.length > 10
    }
}