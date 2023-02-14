import Fraction.Companion.over

data class Fraction(val numerator: Int, val denominator: Int) : Comparable<Fraction>, () -> Double {
    operator fun unaryPlus() = this
    operator fun unaryMinus() = this.copy(-numerator)

    override operator fun invoke() = evaluate()

    private fun evaluate() = numerator.toDouble() / denominator

    override operator fun compareTo(other: Fraction) = if (this() > other()) 1 else if (this() == other()) 0 else -1

    operator fun times(other: Fraction) = (this.numerator * other.numerator) over (this.denominator * other.denominator)
    operator fun times(other: Int) = copy(numerator = numerator * other)

    operator fun get(idx: Int) = when(idx) {
        0 -> numerator
        1 -> denominator
        else -> error("Idx must be 0 or 1")
    }

    class FractionRange(
        override val start: Fraction,
        override val endInclusive: Fraction
     ): ClosedRange<Fraction>, Iterable<Fraction> {
        override operator fun iterator() = object : Iterator<Fraction> {
            var cur = start
            override fun hasNext() = cur <= endInclusive

            override fun next(): Fraction {
                val toReturn = cur
                cur = cur.copy(cur.numerator + 1)
                return toReturn
            }
        }
    }

    operator fun rangeTo(other: Fraction) = FractionRange(this, other)

    companion object {
        infix fun Int.over(other: Int) = Fraction(this, other)
        operator fun Int.times(other: Fraction) = other.copy(numerator = other.numerator * this)
    }

    override fun toString(): String = "$numerator/$denominator"
}

fun main() {
    val f = 5 over 7
    val f2 = 10 over 13

    val res = f * 6

    println(f < f2)
    val range = (4 over 3)..(9 over 5)

    println(f in range)

    for (x in range) {
        println(x)
    }

    val doubleFun : () -> Double = f2
    println(doubleFun())

    println(f())
    println(f[0])
}