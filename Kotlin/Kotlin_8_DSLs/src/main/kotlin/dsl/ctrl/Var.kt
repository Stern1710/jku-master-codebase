package dsl.ctrl

import kotlin.reflect.KProperty

class Var<T>(init: T) {
    private var v : T = init

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return v
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.v = value
    }
}

