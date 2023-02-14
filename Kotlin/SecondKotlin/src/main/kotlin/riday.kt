data class Cow(
    val name: String ="Berta",
) {
    var milkProduced: Double = 0.0

    constructor(name: String ="Berta", milkProduced: Double) : this(name) {
        this.milkProduced = milkProduced
    }

    fun milk(): Double {
        val amount = Math.random() * 20.0
        this.milkProduced += amount
        return amount
    }
}

fun processNullableEverything(list: List<String?>?) = list?.forEach { print(("${it ?: ""} "))
}

fun main() {
    val cow = Cow()
    val cow2 = Cow("Google")

    val cowSet = setOf(cow, cow2)

    println(cow.hashCode())
    println(cow in cowSet) //True
    cow.milk()
    println(cow.hashCode())
    println(cow in cowSet) //False as the amount of milk has changed
    //After change of the primary constructor and adding a secondary one, both in operators now produce true

    val myList = listOf<String?>("Hello", null, "World", null)
    processNullableEverything(myList)
}