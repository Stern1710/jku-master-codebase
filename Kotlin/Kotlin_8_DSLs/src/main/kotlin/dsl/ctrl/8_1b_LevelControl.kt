package dsl.ctrl

import dsl.ctrl.States.*
import dsl.ctrl.TankStates.*

enum class States {
    EMPTY, FILLED, FILLING, STOPPED
}

private const val FULL_LEVEL = 100.0
private const val EMPTY_LEVEL = 10.0
const val MAX_LEVEL = 120.0
const val INFLOW = 5.0
const val OUTTAKE = 2.0

class LevelController(levelInp : Var<Double>, stopInp: Var<Boolean>, pumpOut : Var<Boolean>) : StateModel<States>() {

    private var pump : Boolean by pumpOut
    private var level : Double by levelInp
    private var stop : Boolean by stopInp

    init {
        state = EMPTY
    }

    override fun transition() {
        inState(EMPTY) {
            instantly {
                pump = true
                goto(FILLING)
            }
        }
        inState(FILLING) {
            on({ stop }) {
                pump = false
                goto(STOPPED)
            }
            on ( { level >= FULL_LEVEL })  {
                pump = false
                goto(FILLED)
            }
        }
        inState(FILLED) {
            on ({ level < EMPTY_LEVEL }) {
                goto(EMPTY)
            }
        }
        inState(STOPPED) {
            on ({ !stop } ) {
                pump = true
                goto(FILLING)
            }
        }
    }

    override fun toString(): String = "LevelControl [level=$level, pump=$pump, stop=$stop, state=${state}]"
}

enum class TankStates {
    UNDERFLOW, OVERFLOW, NORMAL
}


class Tank(inflowInp: Var<Double>, outtakeInp: Var<Double>, levelOut: Var<Double>) : StateModel<TankStates>() {

    private var level : Double by levelOut
    private var inflow : Double by inflowInp
    private var outtake : Double by outtakeInp

    init {
        state = NORMAL
    }

    override fun transition() {

        inState(NORMAL) {
            level += inflow - outtake
            if (level < 0.0) {
                goto(UNDERFLOW)
            } else if (level > MAX_LEVEL) {
                goto(OVERFLOW)
            }
        }
        inState(UNDERFLOW) {
            if (level <= 0.0 && inflow <= outtake) {
                level = 0.0
            } else {
                level += inflow - outtake
                goto(NORMAL)
            }
        }
        inState(OVERFLOW) {
            if (level >= MAX_LEVEL && outtake > inflow) {
                level = level + inflow - outtake
                goto(NORMAL)
            }
        }
    }

    override fun toString(): String = "Tank [level=$level, inflow=$inflow, outflow=$outtake, state=${state}]"
}

enum class TankSystemState {
    RUNNING
}
object TankSystem : StateModel<TankSystemState>() {

    val levelVar = Var(5.0)
    val pumpVar = Var(false)
    val stopVar = Var(false)
    val inflowVar = Var(0.0)
    val outtakeVar = Var(2.0)

    val ctrl = LevelController(levelInp = levelVar, stopInp = stopVar, pumpOut = pumpVar)
    val tank = Tank(inflowInp = inflowVar, outtakeInp = outtakeVar, levelOut = levelVar)

    var inflow : Double by inflowVar
    var pump : Boolean by pumpVar
    var stop : Boolean by stopVar

    init {
        state = TankSystemState.RUNNING
    }

    override fun transition() {
        inflow = if (pump) INFLOW else 0.0
        tank.exec()
        ctrl.exec()
    }

}

fun main() {

    println("Use commands:")
    println("   term - to terminate ")
    println("   stop - to stop filling ")
    println("   cont - to continue filling after stopped ")
    println("   any other input to execute next step ")
    println()
    var line : String? = ""
    while (line != null && ! line.startsWith("term")) {
        if (line.startsWith("stop")) {
            TankSystem.stop = true
        } else if (line.startsWith("cont")) {
            TankSystem.stop = false
        }
        TankSystem.exec()
        println(TankSystem.tank)
        println(TankSystem.ctrl)

        line = readLine()
    }
}
