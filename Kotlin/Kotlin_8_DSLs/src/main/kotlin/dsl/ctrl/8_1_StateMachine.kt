package dsl.ctrl

import java.util.NoSuchElementException
import kotlin.reflect.KProperty

abstract class StateModel<S : Enum<S>>()  {

    private val stateMap = HashMap<S, State>()

    private lateinit var current: State
    private var nxt: S? = null

    var state : S
        get() = current.s //current?.s?:throw NoSuchElementException("state uninitialized")
        set(value) {
            current = getState(value)
        }

    private var next : S
        get() = nxt?:current.s
        set(value) {
            nxt = value
        }

    private fun getState(s: S) : State {
        stateMap[s]?.run {
            return this
        }?:State(s).run{
            stateMap[s] = this
            return this
        }
    }

    protected abstract fun transition()

    fun exec() {
        transition()
        current = getState(next)
    }

    fun initial(initialize: () -> Unit) {
        initialize()
    }

    fun inState(s : S, trans: State.() -> Unit) : Unit {
        if (current?.s == s?:false) {
            current?.trans()
        }
    }

    inner class State(val s : S) {
        fun on(condition: () -> Boolean, action : () -> Unit) : Unit {
            if (condition()) action()
        }

        fun instantly(action : () -> Unit) : Unit {
            action()
        }

        infix fun goto(n: S) {
            next = n
        }
    }
}


