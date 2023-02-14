fun main(args: Array<String>) {
    println("Hello World!")
    val p1 = Point(1, 3)
    val p2 = Point(5, 1)

    val pp = p1+p2
    val pm = p1-p2

    var s = "P1: ${p1}\nP2: $p2\n"
    s = s.points(pp)
    s = s.points(pm)
    print(s)

    val list = listOf(p1, p2, pp, pm)
    for (p in list) {
        print(p)
    }
}

data class Point (val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y+other.y)
    operator fun minus(other: Point) = Point(x-other.x, y-other.y)
}

fun String.points(point: Point) : String  = this + point.toString() + "\n"

fun Rectangle.printWeird() {
    println("This is weird indeed!")
}
