interface SoundGenerator {
    fun start()
    fun makeSound()
}

class Siren: SoundGenerator {
    override fun start() {
        println("... siren is starting ...")
    }

    override fun makeSound() {
        start()
        println("Wiiiuuuuwhiiiiiuuuaahahha")
    }
}

class DogSiren: SoundGenerator {
    override fun start() {
        println("*opens eyes*")
    }

    override fun makeSound() {
        start()
        println("WHUUUUUFFF")
    }

}

class FireDepartment(val nCars: Int, soundGenerator: SoundGenerator = Siren()) : SoundGenerator by soundGenerator {
    override fun start() {
        println("This is the Fire Department! WILL THE REAL SLIM SHADY PLEASE STAND UP?")
    }
}

fun main() {
    val fd1 = FireDepartment(4)
    fd1.makeSound()
}