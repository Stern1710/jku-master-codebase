sealed interface Shape{
    fun toAsciiArt() : String
}

data class Line(val x: Int, val length: Int = 1) : Shape {

    override fun toAsciiArt() : String {
        var s: String = ""

        for (i in 0 until x) {
            s += " "
        }
        s += "X"
        for (i in 0 until length) {
            s += "---"
        }
        return s + "X"
    }

    constructor(x: Int, length: Int, message: String) : this(x, length) {
        println(message)
    }

    companion object {
        fun definition() {
            println("A line has a start point and a horizontal length")
        }
    }
}

data class Rectangle (val x: Int, val y: Int, val len: Int, val width: Int) : Shape {
    operator fun plus(other: Rectangle) = Rectangle(x+other.x, y+other.y, len+other.len, width+other.width)
    operator fun minus(other: Rectangle) = Rectangle(x-other.x, y-other.y, len-other.len, width-other.width)

    infix fun add (other: Rectangle) : Rectangle = Rectangle(x+other.x, y+other.y, len+other.len, width+other.len)

    override fun toAsciiArt(): String {
        var s = printLine(String())

        for (i in 0..width) {
            for (i in 0..x) {
                s += " "
            }
            s += "|"
            for (i in 0..len) {
                s += "   "
            }
            s += "|\n"
        }

        s = printLine(s)

        return s
    }

    private fun printLine(s: String) : String {
        var s = s
        for (i in 0..x) {
            s += " "
        }
        s += "X"
        for (i in 0..len) {
            s += "---"
        }
        s += "X\n"

        return s
    }

    override fun toString(): String = "Rectangle:\n  x: $x\n  y: $y\n  length: $len\n  width: $width"
}

fun main() {
    val r1: Rectangle = Rectangle(3, 5, 10, 5)
    val l1: Line = Line(3, 10, "I got created")

    val r2 = Rectangle(1, 1, 5, 5)
    val r3 = r1 add r2

    val shapes = listOf(r1, l1, r2)
    for (s in shapes) {
        println(s.toAsciiArt())
        defineShape(s)
    }

    r1.printWeird()
    println(r3)
    print(r3.toAsciiArt())

    Line.definition()

    //val myName = getName()
    //println(myName?.uppercase(Locale.getDefault()) ?: throw NullPointerException("Name is null :("))
}

fun getName(): String? {
    print("Name: ")
    val name = readln()
    return if (name == "") null else name
}

private fun defineShape(s: Shape) : Unit = when(s) {
    is Rectangle -> println("Rectangle")
    is Line -> println("Line")
}