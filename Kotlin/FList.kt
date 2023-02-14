package fp

abstract sealed class FList<out E> {

    abstract val isEmpty: Boolean
    override fun toString(): String {
        val elemsString = foldLeft("") { s, e -> s + ", " + e.toString() }
        return "[${elemsString.substring(2)}]"
    }

    fun forEach(action: (E) -> Unit) {
        when (this) {
            is Cons -> {
                action(head)
                tail.forEach(action)
            }
            is Nil -> {
            }
        }
    }

    inline fun iterate(action: (E) -> Unit) {
        var l: FList<E> = this
        while (l is Cons) {
            action(l.head)
            l = l.tail
        }
    }

    fun filter(test: (E) -> Boolean): FList<E> =
        when (this) {
            is Cons -> if (test(head)) Cons(head, tail.filter(test)) else tail.filter(test)
            is Nil -> Nil
        }

    inline fun <reified T> filterTs() : FList<T> {
        val mList = mutableListOf<T>()
        iterate {
            if (it is T) {
                mList.add(it)
            }
        }
        return fromIterable(mList)
    }

    fun <R> map(fn: (E) -> R): FList<R> =
        when (this) {
            is Cons -> Cons(fn(head), tail.map(fn))
            is Nil -> Nil
        }

    fun <R> foldRight(z: R, f: (E, R) -> R): R =
        when (this) {
            is Nil -> z
            is Cons -> f(head, tail.foldRight(z, f))
        }

    inline fun <R> foldLeft(z: R, acc: (R, E) -> R): R {
        var result = z
        iterate {
            result = acc(result, it)
        }
        return result
    }

    fun joinToString(sep: String = ", ", start: String = "", end: String = "",
                     mapper: (E) -> String = { e -> e.toString()}) : String {
        val mapped = this.map( mapper )
        val elemsStrng = mapped.foldLeft("") { s, e -> "$s$sep$e" }
        return "$start${elemsStrng.substring(sep.length)}$end"
    }

    fun <R> map2(fn: (E) -> R): FList<R> =
        foldRight(empty()) { e, l -> Cons(fn(e), l) }

    fun filter2(test: (E) -> Boolean): FList<E> =
        foldLeft(empty()) { r, e -> if (test(e)) Cons(e, r) else r }

    inline fun all(pred: (E) -> Boolean) : Boolean {
        iterate {
            if (! pred(it)) return false;
        }
        return true
    }

    inline fun any(pred: (E) -> Boolean) : Boolean {
        iterate {
            if (pred(it)) return true;
        }
        return false
    }

    companion object {

        fun <A> empty(): FList<A> = Nil

        inline fun <E> flistOf(vararg elems: E): FList<E> {
            return fromIterable(elems.toList())
        }

        inline fun <E> fromIterable(iterable: Iterable<E>): FList<E> {
            var fList: FList<E> = empty()
            iterable.reversed().forEach {
                fList = Cons(it, fList)
            }
            return fList
        }

    }
}

object Nil : FList<Nothing>() {
    override val isEmpty: Boolean = true
    override fun toString(): String = "[]"
}

data class Cons<out E>(val head: E, val tail: FList<E>) : FList<E>() {
    override val isEmpty: Boolean = false
    override fun toString(): String {
        return super.toString()
    }
}

fun main() {
    val l0 = Nil
    val l1 = Cons(1, l0)
    val l2 = Cons(2, l1)
    val l2A = Cons("A", l1)

    val lStr = l2A.filterTs<String>()
    println("lStr = $lStr")

    l2.forEach { println(it) }

    l2.map { it.toString() }.forEach { println(it) }
    l2.filter { it % 2 == 1 }.iterate { println(it) }

    val ns = FList.flistOf(1, 2, -1, 4, -5, 6)
    println(ns.toString())

    ns.iterate {
        println(it)
    }

    println(ns.joinToString(start = "{", end = "}") {e -> String.format("%03d", e) })
    println(ns.joinToString(start = "{", end = "}") )

}
