package sternbauer.eu

import java.awt.BorderLayout
import java.awt.Container
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.*
import kotlin.system.exitProcess

//Model
data class Model<T>(val initial: T) {

    private val callbacks = mutableListOf<() -> Unit>()

    var state: T = initial
        get() = field
        set(s: T) {
            field = s
            notifyListeners()
        }

    fun notifyListeners(): Unit {
        for (callback in callbacks) {
            callback()
        }
    }

    fun <C : JComponent> register(comp: C, fn: C.(model: Model<T>) -> Unit) {
        callbacks.add({ comp.fn(this) })
    }
}

fun <T> modelOf(initial: T): Model<T> {
    return Model(initial)
}

//Frame
fun frame(title: String, x: Int, y: Int, init: JFrameExt.() -> Unit): JFrameExt {
    val fExt = JFrameExt(title)
    fExt.setLocation(x, y)
    fExt.init()
    return fExt
}

class JFrameExt(t: String) : JFrame(t) {
    //Operations for opening/closing the window
    fun exitOnClose() {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }

    fun open() {
        pack()
        isVisible = true
    }

    //Define elements for field, label, buttons
    fun field(w: Int) = JTextField(w)
    fun label(s: String) = JLabel(s)
    fun button(s: String) = JButton(s)

    fun menuBar(init: JMenuBar.() -> Unit) {
        jMenuBar = JMenuBar()
        jMenuBar.init()
    }

    fun JMenuBar.menu(name: String, init: JMenu.() -> Unit) {
        val menu = JMenu(name)
        this.add(menu)
        menu.init()
    }

    fun JMenu.item(name: String): JMenuItem {
        val item = JMenuItem(name)
        this.add(item)
        return item
    }

    //Content plane
    fun content(init: Container.() -> Unit): Container {
        contentPane.init()
        return contentPane
    }

    fun Container.borderLayout(init: BorderLayoutBuilder.() -> Unit): JPanel {
        val builder = BorderLayoutBuilder()
        builder.layout = BorderLayout()
        this.add(builder)
        builder.init()
        return builder
    }

    fun Container.flowLayout(pos: Int = FlowLayout.LEFT, init: FlowLayoutBuilder.() -> Unit): JPanel {
        val builder = FlowLayoutBuilder()
        builder.layout = FlowLayout(pos)
        this.add(builder)
        builder.init()
        return builder
    }

    fun Container.gridLayout(rows: Int, cols: Int, init: GridLayoutBuilder.() -> Unit): JPanel {
        val builder = GridLayoutBuilder(rows, cols)
        builder.layout = GridLayout(rows, cols)
        this.add(builder)
        builder.init()
        return builder
    }

    //Add event listeners for items
    infix fun JMenuItem.onEvent(fn: () -> Unit) {
        addActionListener { fn() }
    }

    infix fun JButton.onEvent(fn: () -> Unit) {
        addActionListener { fn() }
    }

    infix fun JTextField.onEvent(fn: () -> Unit) {
        addActionListener { fn() }
    }

    fun <C : JComponent, T> C.updateOn(model: Model<T>, fn: C.(model: Model<T>) -> Unit) {
        model.register(this, fn)
    }

    //Helper classes for complicated layout building
    open class LayoutBuilder : JPanel() {
        fun <C : JComponent> comp(component: C, init: C.() -> Unit = {}): C {
            this.add(component)
            component.init()
            return component
        }

        fun border() {
            this.border = BorderFactory.createEtchedBorder()
        }
    }

    class BorderLayoutBuilder : LayoutBuilder() {
        fun <C : JComponent> north(comp: C, init: C.() -> Unit = {}): C {
            return comp(BorderLayout.NORTH, comp, init)
        }

        fun <C : JComponent> south(comp: C, init: C.() -> Unit = {}): C {
            return comp(BorderLayout.SOUTH, comp, init)
        }

        fun <C : JComponent> center(comp: C, init: C.() -> Unit = {}): C {
            return comp(BorderLayout.CENTER, comp, init)
        }

        private fun <C : JComponent> comp(pos: String, component: C, init: C.() -> Unit): C {
            this.add(component, pos)
            component.init()
            return component
        }
    }

    class FlowLayoutBuilder : LayoutBuilder() {}

    class GridLayoutBuilder(val rows: Int, val cols: Int) : LayoutBuilder() {
        fun <C : JComponent> comp(component: C, row: Int, col: Int, init: C.() -> Unit = {}): C {
            if (row >= rows || col >= cols) {
                error("Invalid index ($row, $col) for grid layout of size ($rows, $cols). Cannot create")
            }
            this.add(component, row, col)
            component.init()
            return component
        }
    }
}

//Conversion from/to Fahrenheit and Celsius
fun getFahrenheit(temp: Int) = temp * 9 / 5 + 32
fun getCelsius(temp: Int) = (temp - 32) * 5 / 9

fun main() {
    val tempModel = modelOf(0)

    val frame = frame("Convert temperatures", 500, 200) {
        exitOnClose()

        val celsius = field(6)
        val fahrenh = field(6)
        val message = label("${tempModel.state} C = ${getFahrenheit(tempModel.state)} F")

        menuBar {
            menu("File") {
                item("Exit") onEvent { exitProcess(0) }
            }
            menu("Edit") {
                item("Reset") onEvent { tempModel.state = 0 }
            }
        }

        content {
            borderLayout {
                north(flowLayout(FlowLayout.CENTER) {
                    border()
                    comp(button("Reset")) onEvent {
                        tempModel.state = 0
                    }
                })
                center(flowLayout {
                    border()
                    comp(button("-")) onEvent {
                        tempModel.state -= 1
                    }
                    comp(celsius) {
                        text = "${tempModel.state}"
                        onEvent {
                            val c = text.filter { it.isDigit() || it == '-' }.toInt()
                            tempModel.state = c
                        }
                        updateOn(tempModel) {
                            text = "${it.state}"
                        }
                    }
                    comp(button("+")) onEvent {
                        tempModel.state += 1
                    }
                    comp(label("Celsius = "))

                    comp(fahrenh) {
                        text = "${getFahrenheit(tempModel.state)}"
                        onEvent {
                            val f = fahrenh.text.filter { it.isDigit() || it == '-' }.toInt()
                            tempModel.state = getCelsius(f)
                        }
                        updateOn(tempModel) {
                            text = "${getFahrenheit(it.state)}"
                        }
                    }
                    comp(label("Fahrenheit"))
                })
                south(message) {
                    border()
                    text = "${tempModel.state} C = ${getFahrenheit(tempModel.state)} F"
                    updateOn(tempModel) {
                        text = "${it.state} C = ${getFahrenheit(it.state)} F"
                    }
                }
            }
        }
    }

    frame.open()
}