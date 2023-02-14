import Fraction.Companion.over
import java.lang.Exception
import java.util.function.BiPredicate

// Have to write return type out
fun half(x : Int) : Int {
    return x/2
}
//Single expression method: Do not have to provide the return type
fun half2(x: Int)  = x/2.0

fun foo (x: Int, y: Int, z: Int) : Int {
    println("x: $x, y: $y, z: $z")
    return x + y - z
}

// 2 ways to exit a Nothing method/function
fun wth() : Nothing {
    //Endless loop
    while(true) {
        //And exception
        throw Exception()

    }
}
// More on Nothing return type
fun fooTwo(str: String?, y: Int, z: Int)  {
    val res : Int = str?.length ?: error("Did not work")
}

//Another single expression function
fun grade(x: Int) = when(x) {
    1,2,3,4 -> if (x <= 2) "Good" else "Okay"
    5 -> "Negative"
    else -> error("That is illegal!")
}

fun abc() : Int {
    return return return 6
}

// My classes, open mean open for subtyping
open class Dog(val name : String) {
    var hunger = 0
    var isSleepy : Boolean = false

    open fun run(distance: Int = 20, where : String = "Forest") {
        hunger = Math.min(100, distance+hunger);
        println("Running on $where, distance is $distance")
    }

    fun eat(amount: Int) {
        hunger = Math.max(0, hunger - amount)
    }
}

class SleepyDog(name: String) : Dog(name) {
    override fun run(distance: Int, where: String) {
        super.run(distance, where)
        isSleepy = true
    }
}

class Fraction(
    val numerator: Int = 1,
    val denominator: Int = 1
) {
    //infix must have 1 paramter only
    infix fun hasSameN(other: Fraction) = this.numerator == other.numerator
    infix fun hasSameD(other: Fraction) = this.denominator == other.denominator

    //"Equivalent" to Java statics
    companion object {
        infix fun Int.over(other: Int) = Fraction(this, other)
    }
}

/*
fun main() {
    val billy = SleepyDog("Billy")
    billy.run()
    billy.run(where = "Street", distance = 4)

    val value = foo(6, y=7, z = 8)
}
*/

/*
fun main() {
    val f1 = Fraction(1, 6)
    val f2 = Fraction(9, 6)
    val f3 = 4 over 9

    val sameDom = f1 hasSameD f2
}
*/

fun main() {
    val mutableList = mutableListOf("Markus", "Herbert", "Alex")
    val list : List<String> = listOf("This is not a good idea")

    mutableList.clearAndPrint() //okay
    // list.clearAndPrint() //Totally does not work as clearAndPrint only works for mutable lists

    val f : (Int) -> Int = {it + 1}
    println(f.invoke(1))
    println(f(1))
}

fun MutableList<*>.clearAndPrint() {
    while (this.size > 0) {
        print("${this.removeAt(0)} ")
    }
    println()
}

//Higher Order Function
fun<A,B> mapif(data: Collection<A>, predicate: (A) -> Boolean, mapper: (A) -> (B)) {
    val list = mutableListOf<B>()
    for (a in data) {
        if (predicate(a)) {
            list += mapper(a)
        }
    }
}