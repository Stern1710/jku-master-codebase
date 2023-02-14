import java.io.File

// 1. Use data classes for DTOs / POJOs
data class Customer(val name: String, val email: String)

// 2. Use default values instead of overloading
fun infoMail(message: String, header: String = "Info", receiver: String = "all@jku.at") {}

// 3. How to filter a list
val list = listOf(-4, -3, -2, -1, 0, 1, 2, 3, 4)
val positives = list.filter { it > 0 }

// 4. Containment checks (besides other shorthand operators)
val isFiveContained = 5 in list

// 5. String interpolation
val result = "Is five contained in list: $isFiveContained"

// 6. Instance checks
fun handle(x: Any): Double =
    when (x) {
        is String -> x.length.toDouble()
        is Int -> Math.pow(x.toDouble(), 2.0)
        else -> 1.0
    }

// 7. Read-only lists
val l = listOf("Kotlin", "is", "also", "an", "Island")

// 7.a. Mutable lists
val ml = mutableListOf("Kotlin", "is", "also", "an", "Island")

// 8. Read-only maps
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

// 8.a. Mutable maps
val mdigits = mutableMapOf(
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

// 9. Access a map entry (set and get via brackets)
fun wordsToDigits(l: List<String>): List<Int> {
    val res = mutableListOf<Int>()
    for (s in l) {
        val digit = digits[s]
        if (digit != null) {
            res += digit
        }
    }
    return res
}

// 10. Traverse a map or a list of pairs (with destruction)
fun printDigits() {
    for ((k, v) in digits) {
        println("$k -> $v")
    }

    for (entry in digits) {
        println("${entry.key} -> ${entry.value}")
    }
}

// 11. Iterate over a range
fun iterations() {
    for (i in 1..12) {
        println(i)
    }  // closed range: includes 12
    for (i in 1 until 8) {
        println(i)
    } // half-open range: does not include 8
    for (i in 2..10 step 2) {
        println(i)
    }
    for (i in 10 downTo 1) {
        println(i)
    }
    (1..10).forEach { println(it) }
    repeat(15) { print(it) }
}

// 12. Layz property
val p: Char by lazy {
    // Calculated once on first access
    ((Math.random() * 100).toInt() + 32).toChar()
}

// 13. Use extension functions
fun String.initialUpperCase(): String {
    if (!this.isEmpty()) {
        return this[0].uppercase() + substring(1)
    }
    return this
}

// 14. Create a singleton
object Resources {
    val version = "1.0.0"
    val author = "Mr. Dünsch"
}

// 15. Instantiate an abstract class
abstract class MyAbstractClass {
    abstract fun doSomething()
    abstract fun sleep()
}

fun doIt() {
    // Anonymous class
    val myObject = object : MyAbstractClass() {
        override fun doSomething() {
            println("doSomething")
        }

        override fun sleep() {
            println("sleep")
        }
    }
    myObject.doSomething()
}

// 16. Elvis operator (if-not-null-else shorthand)
val fileCount = File("Folder").listFiles()?.size ?: 0

// 17. Throw an exception / return if null
fun exitWithError() {
    val fife: Int = digits["fife"] ?: error("fife not found in digits map")
    println(fife)
}

fun exitWithReturn() {
    val fife: Int = digits["fife"] ?: return
    println(fife)
}

// 18. Get first item of a possibly empty collection
val firstPositive: Int = positives.firstOrNull() ?: -1

// 19. Execute if not null
fun executeIfFifeIsFound() {
    // Learn more about scope functions in the upcoming lectures
    digits["fife"]?.let { fife ->
        println("fife is the digit $fife")
    }
}

// 19.b. Scope functions (such as apply)
class ClassWithConstructorparameterlessProperty() {
    var prop: String = ""
}

val c1 = ClassWithConstructorparameterlessProperty().apply { prop = "Initialized in apply" }
val c2 = ClassWithConstructorparameterlessProperty().also { it.prop = "Initialized in apply" }

// 19.c. Call multiple methods on an object instance ("with" scope function)
class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
}

fun move() {
    val myTurtle = Turtle()
    with(myTurtle) {
        // in this block, myTurtle is "this"
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}
// instead of
fun longMove() {
    val myTurtle = Turtle()
    myTurtle.penDown()
    for (i in 1..4) {
        myTurtle.forward(100.0)
        myTurtle.turn(90.0)
    }
    myTurtle.penUp()
}

// 20. Return on when statement
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

// 21. If-expression
val y = if (firstPositive == 1) {
    "one"
} else if (firstPositive == 2) {
    "two"
} else {
    "other"
}

// 22. Single-expression functions
fun transform2(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> throw IllegalArgumentException("Invalid color param value")
}