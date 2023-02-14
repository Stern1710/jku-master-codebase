import java.util.Comparator
import java.util.Random

// This is typical kotlin stuff

//Use a data class for POJO
data class Customer(val name: String, val email: String)

//use default values instead of function overloading
fun infoMail(message: String, header: String ="Info!", receiver: String ="all@kju.at") {
    TODO()
}

// filter lists
val list = listOf(-4,-3,-2,-1,0,1,2,3,4)
val positives = list.filter { it > 0 }

// 4. containment checks
val isFiveContained = 5 in -3 until 8

// 5. instance checks
fun handle(x: Any) : Int = when(x) {
    is String -> x.length
    is Int -> x
    is Random -> 10
    else -> 0
}

// 6. string interpolation
val result  = "the result is ${handle(5) * 10}, yay, $list"

// 7. immutable data structure + very niiice builders
val l1 = listOf<Int>(3,4,5,6,7,8,9)
val l = buildList<Int> {
    add(5)
    add(7)
    val calc = 6+6+7
    add(calc)
}

// 8. mutablee data structures + nice builders
val mutL = mutableListOf("Markus", null, "Weniger", "loves", null, "Kotlin")

// 9. maps
val digits = mapOf(
    "zero" to 0,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

// 10. short-hand map access
fun wordsToDigits(l : List<String>) : List<Int> {
    val result = mutableListOf<Int>()
    for (s in l) {
        //val digit : Int = digits[s] ?: -1 //Do elvis as digits[s] could return null
        result += digits[s] ?: -1
    }

    return result
}

// 11. travers a map (using destructoring)
fun printDigits() {
    for ((key, value) in digits) {
        println("key: $key\nval: $value")
    }
}

//12. iterate over ranges
fun iterations() {
    for (x in 1..12) {
        println(x)
    }
    for (x in 1 until 12) {
        println(x)
    }

    for (x in 1..12 step 2) {
        println(x)
    }

    for(x in 10 downTo 0 step 3) {
        println(x)
    }

    (1..10).forEach { println(it) }

    repeat(10) { println(it) }
}

// 13
fun String?.initialUperCase() : String? {
    if(!this.isNullOrEmpty()) {
        return this[0].uppercase() + this.substring(1)
    }
    return this
}

//Singleton objects
object FormattingHelper {
    fun foo() {

    }
}

//Anonymous object
fun main() {
    val myComp = object: Comparator<String> {
        override fun compare(o1: String?, o2: String?): Int {
            TODO("Not yet implemented")
        }
    }
}

//Elvis operator, whup whup
fun exitWithError() {
    val five : Int = digits["fife"] ?: error("fife, nice try oida")
    //continue working with non-nullable fife variable
}

fun exitWithReturn() {
    val fife: Int = digits["fife"] ?: return
}

val firstEvenPositive = positives.firstOrNull {it % 2 == 0}

//Scope something
fun executeIfFifeIsFound() {
    digits["fife"]?.let { found ->  println("Fife was found and is $found")}
}

class Turtle {
    fun penUp() {}
    fun penDown() {}
    fun goUp() {}
    fun goDown() {}
}

//Scope function with, apply, etc.
fun workWithTurtle() {
    val turtle = Turtle()
    with(turtle) {
        penDown()
        goUp()
        goUp()
        penUp()
    }
}

fun transform(color: String) = when(color) {
    "Red" -> 0
    "Black" -> 1
    "Green" -> 2
    "Beer" -> 9001 // :)
    else -> -1
}