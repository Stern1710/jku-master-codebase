import kotlin.properties.Delegates
import kotlin.reflect.KProperty

interface SoundGenerator {
    fun start()
    fun makeSound()
}

class Siren : SoundGenerator {
    override fun start() {
        println("wrrrmmmmmmmmMMMMMM")
    }

    override fun makeSound() {
        start()
        println("UUUUUUUUUUUUUUwUUUUUUUUUUUUwUUUUUUUUUUUUUU")
    }
}

class DaSoundOfDaPolice : SoundGenerator {
    override fun start() {
        println("Whup Whup")
    }

    override fun makeSound() {
        start()
        println("That's the sound of da poliiice")
    }

}

class Dispatcher: SoundGenerator {
    override fun start() {
        println("Klauen wolltest du die")
    }

    override fun makeSound() {
        start()
        println("ALARM! AAA-LAAAA-AARM!")
    }
}

interface Operation {
    fun doStuff()
}

class Operator: Operation {
    override fun doStuff() {
        println("I AM DOING IT!")
    }
}

class Firewatch (sound: SoundGenerator = Siren()) : SoundGenerator by sound
class PoliceStation : SoundGenerator by DaSoundOfDaPolice(), Operation by Operator()

fun main() {
    val firewatch = Firewatch()
    val pornFirewatch = Firewatch(sound = Dispatcher())
    val police = PoliceStation()

    firewatch.makeSound()
    println("")
    pornFirewatch.makeSound()
    println("")
    police.makeSound()
    police.doStuff()

    println("---------------")

    val form : LoginForm = LoginForm()

    doThrice {
        println(form.isAuthenticated)
        println(form.getUsername)
        println(form.getPassword)
        form.tries++;
    }
    form.getPassword = "new password"
}

object AccessSimulator {
    fun getSuccess(): Boolean {
        println("Creating success chance")
        return Math.random() > 0.5
    }
}

class LoginForm {
    val isAuthenticated: Boolean by lazy {
        AccessSimulator.getSuccess()
    }
    val getUsername: String by FileSaveString<LoginForm, String> {
        "My Name"
    }
    var getPassword: String by LoginFormString { "password" }

    var tries: Int by Delegates.observable(0) {
        property, oldValue, newValue -> println("${property.name} changed from $oldValue to $newValue")
    }
}

class FileSaveString<T, V: Any>(val init: () -> V) {
    private var item : V? = null

    operator fun getValue(ref: T, prop: KProperty<*>) : V {
        if (item == null) {
            println("Init for ${ref} the property ${prop.name}")
            item = init()
        }
        return item!!
    }
}

class LoginFormString (val init: () -> String) {
    private var item: String? = null

    operator fun getValue(ref: LoginForm, prop: KProperty<*>) : String {
        if (item == null) {
            println("Initializing a String")
            item = init()
        }
        return item!!
    }

    operator fun setValue(ref: LoginForm, prop: KProperty<*>, value: String) {
        println("Setting ${prop.name} value from $item to $value")
        item = value
    }
}