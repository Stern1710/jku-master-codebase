fun main() {
    val localWorker = ConstructionWorker(90, 12.5)
    println(localWorker.remark)

    val totalCost = localWorker.cost()
    println("This will be $totalCost please")
    println("----")
    worker.workedHours = localWorker.workedHours
    println("The worker object charges ${worker.cost()}")
    println("----")
    println("The cheaper worker is ${if (worker.cost() > localWorker.cost()) "localWorker" else "worker object"}")
}

interface NormalWorker {
    val rate: Int
    var workedHours: Double

    fun cost() : Double = rate * workedHours
}

class ConstructionWorker (override val rate: Int, override var workedHours: Double): NormalWorker {
    val remark: String
        get() = "This is a snarky remark"
}

val worker = object : NormalWorker {
    override val rate: Int
        get() = 75

    override var workedHours: Double = 0.0
}