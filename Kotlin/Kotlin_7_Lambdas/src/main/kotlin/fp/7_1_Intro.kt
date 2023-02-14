package fp

fun main() {

    println(fac(5))

    println(fac2(5))

    val mutableList = mutableListOf<Int>(1, 2)

    val readOnlyList : List<Int> = mutableList

    mutableList.add(3)

    println("readOnlyList = $readOnlyList")

    val immutableList = mutableList.toList()  // makes copy

    println("immutableList = $immutableList")

}

fun fac(n : Int) : Int {
    println("$n")
    return if (n == 1) 1 else n * fac(n - 1)
}

fun fac2(n: Int) : Int {
    tailrec fun facIter(n : Int, acc : Int) : Int {
        println("$n, $acc")
        return if (n == 1) acc
        else facIter(n - 1, acc * n)
    }
    return facIter(n, 1)
}