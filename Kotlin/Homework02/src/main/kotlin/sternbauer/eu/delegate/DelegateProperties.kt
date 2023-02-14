package sternbauer.eu.delegate

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.reflect.KProperty

private const val dataDir = "./data/properties/"

/*
I hope that idea of providing the mapper from and to String for a generic value is a viable idea.
I have found other ways which the Jedi consider unnatural, so I have not provided them in this hand-in.
Yes, I would have been way fast with a non-generic version, but hey, where is the fun. Even though I seriously need
to put way more effort into getting familiar with the Kotlin way of Generics and the JVM limitations (again).
 */
class FileStorage<T>(val toT: (String) -> T, val fromT: (T) -> String) {
    operator fun getValue(thisRef: Person, property: KProperty<*>): T? {
        val f = File("$dataDir${thisRef.name}_${property.name}.prop")
        if (!f.exists()) return null
        return toT(f.readText())
    }

    operator fun setValue(thisRef: Person, property: KProperty<*>, value: T?) {
        val f = File("$dataDir${thisRef.name}_${property.name}.prop")
        if (f.exists()) f.delete() //delete and start over, when in need to do so
        if (value != null) {
            f.createNewFile()
            f.writeText(fromT(value))
        }
    }

    companion object {
        init { //Make sure the property store directory exists (and I finally could use companion objects)
           Files.createDirectories(Paths.get(dataDir))
        }
    }
}

data class Person(val name: String) {
    var age: Int? by FileStorage(String::toInt, Int::toString)
    var hobby: String? by FileStorage(String::toString, String::toString)

    override fun toString(): String {
        return "Name: $name, Age: $age, Hobby: $hobby"
    }
}

fun main() {// Persons have a name (identifying property), a hobby (delegated to a StringFileStorage) and an age (delegated to an IntFileStorage)
// Also, toString is overridden
// hobby and age are null by default
    val markus = Person("Markus")
    val daniel = Person("Daniel")
    val sandra = Person("Sandra")
// If everything is implemented correct, this prints null hobbies and null ages on the first run, but markus should start completely initialized on a second program run
    println(markus)
    println(daniel)
    println(sandra)
// Set all properties, this writes to six files (and creates them if not existing)
    markus.age = 29
    daniel.age = 24
    sandra.age = 27
    markus.hobby = "Board gaming"
    daniel.hobby = "Geocaching"
    sandra.hobby = "Breakdance"
    println(markus)
    println(daniel)
    println(sandra)
// Deletes the two property files
    daniel.age = null
    sandra.hobby = null
// Two properties show as null (daniel's age and sandra's hobby)
    println(markus)
    println(daniel)
    println(sandra)
}