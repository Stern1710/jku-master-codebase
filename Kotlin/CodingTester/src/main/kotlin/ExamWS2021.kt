data class Person(val name: String?, var age: Int = 20) {
    operator fun plus(aging: Int) = Person(this.name, this.age+aging)
    operator fun plusAssign(aging: Int) {
        age += aging
    }
}

val list: List<Person>? = listOf(Person("Name 1"), Person(null), Person("Third name"))

val person = Person("Age", 23)

fun main() {
    if (list != null) {
        for (p in list) {
            println("Name length: ${p.name?.length ?: 0}")
        }
        (0 until list.size).forEach {//alternative: List.indices
            println("Name length: ${list[it].name?.length ?: 0}")
        }
    }

    val olderP = person + 2
    person += 3
    println(person)
    println(olderP)
    println("------------")

    val vehicle = Vehicle(VType.AIR)
    vehicle.move()
    val bmw = Car(VType.GROUND, "BMW")
    bmw.move()
    println("------------")

    val mixer = Mixer("Super Duper Mixer")
    mixer.describe()
    mixer.use()
    println("Mixer tex: ${mixer.tex}")

    val utility = object : KitchenUtility {
        override val tex: Int
            get() = 30

        override fun use() {
            println("Do stuff")
        }
    }
    println("Object tex: ${mixer.tex}")
    utility.use()
    println("------------")

    val string: String = with(StringBuffer()) {
        this.append("My thing")
        toString()
    }
    println("String content is: $string")

}

enum class VType {
    AIR, GROUND,
    WATER {
        var maxTide: Int = 2
            private set;

        fun updateWater(tide: Int) {
            maxTide = tide
        }
    }
}

open class Vehicle(val type: VType) {
    open fun move() {
        println("Vehicle of type $type needs to move")
    }
}

class Car (type: VType, private val brand: String) : Vehicle(type) {
    override fun move() {
        println("$brand of type $type is moving")
    }
}

interface KitchenUtility {
    val tex: Int

    fun use()
    fun describe() = println("This is a kitchen utility")
}

class Mixer(private val desc: String) : KitchenUtility {
    override val tex: Int
        get() = 20;

    override fun use() {
        println("I am using \"$desc\"")
    }

    override fun describe() {
        super.describe()
        println("To be exact, I am a \"$desc\"")
    }
}