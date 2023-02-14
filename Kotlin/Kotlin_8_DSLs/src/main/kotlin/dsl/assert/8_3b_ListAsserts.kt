package dsl.assert

object also

abstract class ListTest<T>(val list: List<T>) {
    infix fun and (also: also) : List<T> = list
}

sealed class testLists
object contain : testLists()
object have : testLists()
object be : testLists()
object beEmpty : testLists()
object notBeEmpty : testLists()

infix fun <T> List<T>.should(contain: contain) : ContainsTest<T> {
    return ContainsTest<T>(this)
}
infix fun <T> List<T>.should(have: have) : HaveTest<T> {
    return HaveTest<T>(this)
}

infix fun <T> List<T>.should(be: be) : BeTest<T> {
    return BeTest<T>(this)
}
infix fun <T> List<T>.should(beEmpty: beEmpty) : List<T> {
    if (! isEmpty()) {
        throw AssertionError("List $this is not empty")
    } else {
        println("OK: List $this is empty")
    }
    return this
}
infix fun <T> List<T>.should(notBeEmpty: notBeEmpty) : List<T> {
    if (isEmpty()) {
        throw AssertionError("List $this is empty")
    } else {
        println("OK: List $this is not empty")
    }
    return this
}

class ContainsTest<T>(l: List<T>) : ListTest<T>(l)   {
    infix fun element(elem: T) : ListTest<T> {
        if (! (list.contains(elem))) {
            throw AssertionError("List $list does not contain $elem")
        } else {
            println("OK: List $list contains $elem")
        }
        return this
    }
}
class HaveTest<T>(l: List<T>) : ListTest<T>(l)   {
    infix fun size(size: Int) : ListTest<T> {
        if (list.size != size) {
            throw AssertionError("List $list does not have size $size")
        } else {
            println("OK: List $list has size $size")
        }
        return this
    }
}
class BeTest<T>(l: List<T>) : ListTest<T>(l) {
    infix fun empty(empty: Boolean) : ListTest<T> {
        if (list.isEmpty() != empty) {
            throw AssertionError("List $list is not empty $empty")
        } else {
            println("OK: List $list is empty $empty")
        }
        return this
    }

    infix fun all(pred : (T) -> Boolean) : ListTest<T> {
        if (! list.all(pred)) {
            throw AssertionError("List $list not all fulfill predicate")
        } else {
            println("OK: List $list all fulfill predicate")
        }
        return this
    }

    infix fun none(pred : (T) -> Boolean) : ListTest<T> {
        if (list.any(pred)) {
            throw AssertionError("List $list not none fulfill predicate")
        } else {
            println("OK: List $list none fulfill predicate")
        }
        return this
    }

}

fun main() {
    val lst = mutableListOf<String>("A", "B")

    lst should contain element "A"
    lst should have size 2
    lst should be empty false and also should contain element "B" and also should contain element "A"
    lst should have size 2 and
            also should be all {it.length > 0} and
            also should be all {it[0].isLetterOrDigit()} and
            also should be none {it.length > 4}
    lst should notBeEmpty
}
