package dsl.si

// Unit  --------------------------------------------------------------------

open class Unit(val name: String, val factor: Double) {

    companion object {
        fun <U : Unit> smaller(unit1: U, unit2: U) : U =
            if (unit1.factor < unit2.factor) unit1 else unit2
    }
}

// Quantity -- ----------------------------------------------------------------

open abstract class Quantity<out U : Unit>(val value: Double, val unit : U) {
    protected fun <V : Unit> convert(target: V) : Double = value * unit.factor / target.factor
    override fun toString(): String = "$value ${unit.name}"
}

// Length ---------------------------------------------------------------------

sealed class LengthUnit(n: String, f: Double) : Unit(n, f)
object Meter : LengthUnit("m", 1.0)
object Centimeter : LengthUnit("cm", 0.01)
object Millimeter : LengthUnit("mm", 0.001)
object Kilometer : LengthUnit("km", 1000.0)

data class Length(val l : Double, val lu: LengthUnit) : Quantity<LengthUnit>(l, lu) {

    operator fun plus(other : Length) : Length {
        val rUnit = Unit.smaller(this.unit, other.unit)
        val rValue = this.inUnit(rUnit) + other.inUnit(rUnit)
        return Length(rValue, unit)
    }
    operator fun minus(other : Length) : Length {
        val rUnit = Unit.smaller(this.unit, other.unit)
        val rValue = this.inUnit(rUnit) - other.inUnit(rUnit)
        return Length(rValue, unit)
    }
    operator fun times(times : Double) : Length =
        Length(l * times, unit)
    operator fun div(divider : Double) : Length =
        Length(l / divider, unit)

    operator fun div(dur: Duration) : Velocity =
        if (this.unit == Kilometer) Velocity(inKm / dur.inH, KmPerHour)
        else Velocity(inM / dur.inSec, MeterPerSeconds)
    operator fun div(vel: Velocity) : Duration =
        if (this.unit == Kilometer) Duration(inKm / vel.inKMpH, Hours)
        else Duration(inM / vel.inMpS, Seconds)

    fun asUnit(unit: LengthUnit) = Length(super.convert(unit), unit)
    val asKm : Length
        get() = asUnit(Kilometer)
    val asM : Length
        get() = asUnit(Meter)
    val asCm : Length
        get() = asUnit(Centimeter)
    val asMm : Length
        get() = asUnit(Millimeter)

    fun inUnit(unit: LengthUnit) : Double = asUnit(unit).value
    val inKm : Double
        get() = inUnit(Kilometer)
    val inM : Double
        get() = inUnit(Meter)
    val inCm : Double
        get() = inUnit(Centimeter)
    val inMm : Double
        get() = inUnit(Millimeter)

    override fun toString(): String = super.toString()
}

val Number.m : Length
    get() = Length(this.toDouble(), Meter)
val Number.cm : Length
    get() = Length(this.toDouble(), Centimeter)
val Number.mm : Length
    get() = Length(this.toDouble(), Millimeter)
val Number.km : Length
    get() = Length(this.toDouble(), Kilometer)

// Duration -------------------------------------------------------------------

sealed class TimeUnit(n: String, f: Double) : Unit(n, f)
object Seconds : TimeUnit("sec", 1.0)
object Minutes : TimeUnit("min", 60.0)
object Hours : TimeUnit("h", 3600.0)
object Milliseconds : TimeUnit("ms", 0.001)

data class Duration(val t: Double, val tu: TimeUnit) : Quantity<TimeUnit>(t, tu) {
    operator fun plus(other : Duration) : Duration {
        val rUnit = Unit.smaller(unit, other.unit)
        return Duration(this.convert(rUnit) + other.convert(rUnit), rUnit)
    }
    operator fun minus(other : Duration) : Duration {
        val rUnit = Unit.smaller(unit, other.unit)
        return Duration(this.inUnit(rUnit) - other.inUnit(rUnit), rUnit)
    }
    operator fun times(times : Double) : Duration = Duration(t * times, unit)
    operator fun div(divider : Double) : Duration = Duration(t / divider, unit)
    operator fun times(vel : Velocity) : Length = vel * this

    fun asUnit(unit: TimeUnit) = Duration(super.convert(unit), unit)
    val asSec : Duration
        get() = asUnit(Seconds)
    val asMin : Duration
        get() = asUnit(Minutes)
    val asHours : Duration
        get() = asUnit(Hours)
    val asMilliSec : Duration
        get() = asUnit(Milliseconds)

    fun inUnit(unit: TimeUnit) : Double = asUnit(unit).value
    val inSec : Double
        get() = inUnit(Seconds)
    val inMin : Double
        get() = inUnit(Minutes)
    val inH : Double
        get() = inUnit(Hours)
    val inMs : Double
        get() = inUnit(Milliseconds)

    override fun toString(): String = super.toString()
}

val Number.ms : Duration
    get() = Duration(this.toDouble(), Milliseconds)
val Number.sec : Duration
    get() = Duration(this.toDouble(), Seconds)
val Number.min : Duration
    get() = Duration(this.toDouble(), Minutes)
val Number.h : Duration
    get() = Duration(this.toDouble(), Hours)

// Velocity -------------------------------------------------------------------

sealed class VelUnit(n: String, f: Double) : Unit(n, f)
object MeterPerSeconds : VelUnit("m/sec", 1.0)
object KmPerHour : VelUnit("km/h", 1.0/3.6)

data class Velocity(val v : Double, val u: VelUnit) : Quantity<VelUnit>(v, u)  {
    operator fun plus(other : Velocity) : Velocity {
        val rUnit = Unit.smaller(unit, other.unit)
        return Velocity(this.inUnit(rUnit) + other.inUnit(rUnit), rUnit)
    }
    operator fun minus(other : Velocity) : Velocity {
        val rUnit = Unit.smaller(unit, other.unit)
        return Velocity(this.inUnit(rUnit) - other.inUnit(rUnit), rUnit)
    }
    operator fun times(times : Double) : Velocity =
        Velocity(v * times, unit)
    operator fun div(divider : Double) : Velocity =
        Velocity(v / divider, unit)
    operator fun times(d : Duration) : Length {
         if (unit == KmPerHour) {
             return Length(this.v * d.inH, Kilometer)
         } else {
             return Length(this.v * d.inSec, Meter)
         }
    }

    fun asUnit(unit: VelUnit) = Velocity(super.convert(unit), unit)
    val asMpS : Velocity
        get() = asUnit(MeterPerSeconds)
    val asKMpH : Velocity
        get() = asUnit(KmPerHour)

    fun inUnit(unit: VelUnit) : Double = asUnit(unit).value
    val inMpS : Double
        get() = inUnit(MeterPerSeconds)
    val inKMpH : Double
        get() = inUnit(KmPerHour)

    override fun toString(): String = super.toString()
}

val Number.mSec : Velocity
    get() = Velocity(this.toDouble(), MeterPerSeconds)
val Number.kmH : Velocity
    get() = Velocity(this.toDouble(), KmPerHour)

// main -----------------------------------------------------------------------

fun main() {

    println("1 m = ${1.m.inKm} km")
    println("1 cm = ${1.cm.inM} m")
    println("1 mm = ${1.mm.inCm} cm")
    println("1 km = ${1.km.inM} m")

    println("1 sec = ${1.sec.inMin} min")
    println("1 sec = ${1.sec.inH} h")
    println("1 sec = ${1.sec.inMs} ms")
    println("1 h = ${1.h.inSec} sec")
    println("1 h = ${1.h.inMin} min")
    println("1 h = ${1.h.inMs} ms")
    println("1 min = ${1.min.inSec} sec")
    println("1 min = ${1.min.inH} h")
    println("1 min = ${1.min.inMs} ms")

    println("1 kmH = ${1.kmH.inMpS} m/s")
    println("1 m/s = ${1.mSec.inKMpH} kmH")


    val m100 = 100.m
    val km1 = 1.km
    val km1m100 = 1.km + 100.m
    println("km1m100 = $km1m100")

    val cm1mm10 = 4.mm + 1.cm
    println("cm1mm10 = $cm1mm10")

    val s10 = 10.sec
    println(m100)
    println(s10)

    val m200 = m100 + m100
    println(m200)

    val speed = 100.m / 10.sec
    println("speed = $speed")

    val dist = speed * 1.h
    println("dist = ${dist.inKm} km")

    // half and full marathon

    val marathon = 42.195.km
    val halfMarathon = marathon / 2.0

    val halfMTime = 1.h + 34.min
    val velHalfM : Velocity = halfMarathon / halfMTime
    println("Vel for a half marathon = ${velHalfM.asKMpH} \n(= ${velHalfM.asMpS})")

    val timeFull : Duration = (marathon / velHalfM)
    println("Time for a full marathon = ${timeFull.asHours} (= ${timeFull.asMin})")

    val distFull : Length = velHalfM * timeFull
    println("Distance after $timeFull = ${distFull.asM} (= ${distFull.asKm})")

}

// ----------------------------------------------------------------------------



