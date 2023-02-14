import kotlin.reflect.KClass
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty0

data class Person(
    val firstName: String ="Betty",
    val lastName: String ="Brocollibu",
    val children: List<String> = listOf(),
    val age: Int = 18)
{
    fun print() {
        println(this)
    }

    fun hasLongName() = firstName.length + lastName.length >= 20
}

fun main() {
    val jClass : Class<Person> = Person::class.java

    val betty = Person()
    val bettyAsAny : Any = betty

    val pKClass : KClass<*> = bettyAsAny::class
    println(pKClass.simpleName)

    val printFun = Person::print
    val printFun2 : (Person) -> Unit = Person::print
    val printFun3 : KFunction1<Person, Unit> = Person::print

    printFun(betty)
    printFun2(betty)

    val bettiesPrint = betty::print
    bettiesPrint()

    executeTwice(betty::print)
    executeTwice {
        betty.print()
    }

    val kotlinLengthProp: KProperty0<Int> = "Kotlin"::length
    println(kotlinLengthProp.name) //length
    println(kotlinLengthProp())

    val bettyAgeProp = betty::age
    println(bettyAgeProp())

    val personConstructor = ::Person
    val newPerson = personConstructor("Axel", "Schweiss", listOf(), 7)
}

fun executeTwice(fn : () -> Unit) {
   fn()
   fn()
}