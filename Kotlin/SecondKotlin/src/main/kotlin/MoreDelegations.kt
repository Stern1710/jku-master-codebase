import kotlin.properties.Delegates
import kotlin.reflect.KProperty

object DBSimulation {
    fun getDogName() = "Billy"
    fun getOwnerName() = "Markus"
}

//Lazy delays the loading of the elements up until the first use, so not getting executed when creating a Dog object
class Dog {
    val name: String by lazy {
        println("Load dog name from DB")
        DBSimulation.getDogName()
    }

    val owner: String by lazy {
        println("Load owner name from DB")
        DBSimulation.getOwnerName()
    }
}

class Food(val name: String, initialPrice: Double) {
    //it is that easy to write an observeable and I totally like it
    /*
    var price: Double by Delegates.observable(initialPrice) { prop, oldVal, newVal ->
        println("${prop.name} has been changed $this from $oldVal to $newVal")
    }
    */
    var price: Double by Shouter(5.0)
}

class Shouter<THIS, PROP : Any> (initialValue: PROP){
    var store: PROP = initialValue

    operator fun getValue(thisRef: THIS, property: KProperty<*>): PROP {
        println("${property.name} has been initialized in object $thisRef with value $store")
        return store
    }

    operator fun setValue(thisRef: THIS,  property: KProperty<*>, value: PROP) {
        println("${property.name} has been set in object $thisRef with value $store")
        store = value
    }
}

//Own something
class shoutingLazy<THIS, PROP : Any>(val initFun: () -> PROP) {
    lateinit var store: PROP

    operator fun getValue(thisRef: THIS, property: KProperty<*>): PROP {
        if (!::store.isInitialized) { //Not yet initialize
            store = initFun()
            println("${property.name} has been initialized in object $thisRef with value $store")
        }
        return store
    }
}

val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

//very cool expensive method
fun generateGenes() = CharArray(100) { charPool.random() }.joinToString(separator = "")

class Cat(val name: String = "Mitzi") {
    val genes: String by shoutingLazy {
        generateGenes()
    }
}

fun main() {
    val dog = Dog()
    println(dog.name)
    println(dog.name)
    println(dog.name)

    var schnitzel = Food("Schnitzel", 10.9)
    schnitzel.price += 1
    schnitzel.price = 140.0

    val cat = Cat()
    executeTwice { println(cat.genes) }
}