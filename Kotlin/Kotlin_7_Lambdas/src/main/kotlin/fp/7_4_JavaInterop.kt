package fp

import java.awt.event.ActionListener
import java.time.LocalTime
import javax.swing.Action
import javax.swing.JButton

fun main() {

    val btn : JButton = JButton("OK")
    btn.addActionListener { ae -> println("Action performed") }

    val thread : Thread = Thread Runnable@{
        for (i in 1 .. 100) {
            println(" It's now ${LocalTime.now()}")
            try {
                Thread.sleep(1000)
            } catch (e : InterruptedException) {
                return@Runnable
            }
        }
    }
    //thread.start()

    val actionListener : ActionListener = ActionListener { ae -> println("Action performed") }

    val clock : Runnable = Runnable {
        for (i in 1..100) {
            println(" It's now ${LocalTime.now()}")
            try {
                Thread.sleep(1000)

            } catch (e: InterruptedException) {
                return@Runnable
            }
        }
    }

    val thread2 : Thread = Thread( Runnable {
        for (i in 1 .. 100) {
            println(" It's now ${LocalTime.now()}")
            try { Thread.sleep(1000)} catch (e : InterruptedException) {}
        }
    })
    thread2.start()

}

fun createRunnable(code: () -> Unit) : Runnable = Runnable { code() }