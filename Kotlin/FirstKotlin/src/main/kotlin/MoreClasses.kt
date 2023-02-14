import java.security.spec.NamedParameterSpec
import javax.naming.Name

open class Parent(val firstName: String) {
    open val nameLength
        get() = firstName.length

    open fun doIt() {
        println("Do it")
    }
}

class Child(
    firstName: String,
    val lastName : String
) : Parent(firstName) {
    override val nameLength: Int
        get() = super.nameLength + lastName.length

    override fun doIt() {
        super.doIt()
        println("But in a super duper, childish way!")
    }
}
/*
fun main() {
    val child = Child("Baby", "Markus")
    println("Child has name length $child.nameLength")
}
 */

/*
Define my first interface, jay
 */
//Sealed = Direct subclasses MUST be in the same package --> Compiler can ensure exhaustive checking
sealed interface Named {
    val name: String

    fun print() {
        println(name)
    }

    fun callMe()
}

/* Long ugly way
class Animal(
    name: String
) : Named {
    override val name: String = name
}
 */

class Animal(
    override val name : String,
    val kind: String
) : Named {
    override fun callMe() {
        println("Kim hea du klane Gretzn")
    }
}

class Shop(
    override val name: String
) : Named {
    override fun callMe() {
        println("Welcome to the service hotline of $name")
    }
}

fun main() {
    val somethingWithNamed : Named = if (Math.random() < 0.5) Animal("Rufus", "FlyingBug") else Shop("Fressnapf")

    val result = when(somethingWithNamed) {
        is Animal -> "Animal of kind ${somethingWithNamed.kind}"
        is Shop -> "I like to shop at ${somethingWithNamed.name}"
    }

    println(result)
}