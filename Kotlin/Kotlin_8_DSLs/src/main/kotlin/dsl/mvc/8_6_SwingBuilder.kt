package dsl.mvc

import com.sun.tools.javac.code.TypeAnnotationPosition.field
import java.awt.*
import java.awt.event.ActionListener
import javax.swing.*
import kotlin.reflect.KProperty

// model

data class Model<T>(val initial : T) {

    private val callbacks = mutableListOf<() -> Unit>()

    var state: T = initial
        get() = field
        set(s: T) {
            field = s
            notifyListeners()
        }

    fun notifyListeners() : Unit {
        for (callback in callbacks) {
            callback()
        }
    }
    fun <C: JComponent> register(comp: C, fn: C.(model: Model<T>) -> Unit) {
        callbacks.add({comp.fn(this)})
    }
}

fun <T> modelOf(initial : T) : Model<T> {
    return Model(initial)
}

// frame

fun frame(title : String, init: JFrameExt.() -> Unit) {
    val fExt = JFrameExt(title)
    fExt.init()
}

class JFrameExt(val t: String) : JFrame(t) {
    fun field(w: Int) = JTextField(w)

    fun <C: JComponent, T> C.updateOn(model: Model<T>, fn: C.(model: Model<T>) -> Unit ) {
        model.register(this, fn)
    }
}


fun getFahrenheit(temp : Int ) = temp * 9 / 5 + 32

fun main() {

    val tempModel = modelOf(0)

    val frame = frame("Hallo") {
        title = "Hallo"
        val f = field(6)
        f.updateOn(tempModel) {
            text ="${tempModel.state}"
        }
    }
}
