import kotlin.reflect.KFunction

data class Citizen(val name: String, val age: Int) {
    fun printPerson() {
        println("""
            Name: $name
            Age: $age
            (* _ *)
             --|--
               |
              / \
        """.trimIndent())
    }
}

fun main() {
    val actCiz = Citizen("Max Mustermann", 30)
    val anyCiz: Any = actCiz

    val cizClass = actCiz::class
    val cizJClass = cizClass.java
    val cizKClass = cizJClass.kotlin
    val citActClass = actCiz::class
    val cizAnyClass = anyCiz::class

    val printCiz = Citizen::printPerson
    val printAct: (Citizen) -> Unit = Citizen::printPerson

    printCiz(actCiz)
    printAct(actCiz)

    val printActCiz = actCiz::printPerson
    printActCiz()

    val nameCiz = Citizen::name
    println(nameCiz(actCiz))

    val nameActCiz = actCiz::name
    println(nameActCiz.get())

    val cizCons = ::Citizen
    val betterCiz = cizCons("Martina Musterfrau", 42)
    betterCiz.printPerson()

}