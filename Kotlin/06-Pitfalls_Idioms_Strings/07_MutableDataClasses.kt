data class Cow(val name: String = "Berta", var milkProduced: Double = 0.0) {
    fun milk(): Double {
        val amount = Math.random() * 20.0
        milkProduced += amount
        return amount
    }
}

fun main() {
    val cow1 = Cow("Berta")
    val cow2 = Cow("Brunhilde")
    val set = setOf(cow1, cow2)

    println(cow1 in set)
    cow1.milk()
    println(cow1 in set)
}