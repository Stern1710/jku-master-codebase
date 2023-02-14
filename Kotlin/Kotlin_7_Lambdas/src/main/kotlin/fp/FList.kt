package fp

abstract sealed class FList<out E> {
    abstract val isEmpty: Boolean
    abstract val size: Int

    fun <R> map(fn: (E) -> R): FList<R> = //this: FList
        when (this) {
            is Nil -> Nil
            is Cons -> Cons(fn(head), tail.map(fn))
        }

    fun filter(pred: (E) -> Boolean) : FList<E> =
        when(this){
            is Nil -> Nil
            is Cons -> if (pred(this.head)) Cons(head, tail.filter(pred)) else tail.filter(pred)
        }

    fun foreach(action: (E) -> Unit) : Unit {
        when (this) {
            is Nil -> Nil
            is Cons -> {
                action(head)
                tail.foreach(action)
            }
        }
    }

    inline fun iterate(action: (E) -> Unit) : Unit { //Inline is possible as this is not a recursive call (would need to know on compile how many rec. calls there are
        var l = this
        while (l is Cons) {
            action(l.head)
            l = l.tail
        }
    }

    inline fun <reified T> filterTs() : FList<T> {
        val mList = mutableListOf<T>()
        iterate {
            if (it is T) mList.add(it)
        }
    }
}

data class Cons<out E>(val head: E, val tail: FList<E>) : FList<E>() {
    override val isEmpty = false
    override val size = 1 + tail.size
}

object Nil : FList<Nothing>() {
    override val isEmpty = true
    override val size = 0
}

fun main() {
    var l: FList<Nothing> = Nil

    val x = Cons(1, l)
    val y = Cons(true, x)

    val ints = Cons(1, Cons(2, Nil))
    println(ints)
}
