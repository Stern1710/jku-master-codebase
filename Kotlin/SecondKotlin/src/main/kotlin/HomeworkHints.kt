import java.io.File
import kotlin.reflect.KProperty

class IntFileStorage {
    operator fun getValue(thisRef: Person, prop: KProperty<*>) :Int? {
        val f = File("${thisRef.lastName}_${prop.name}.prop")
    }

    operator fun setValue(thisRef: Person, prop: KProperty<*>, value: Int?) {
        val f = File("${thisRef.lastName}_${prop.name}.prop")
        if (value != null) {
            f.createIfNonExist
            f.writeTo
        } else {
            f.delete()
        }
    }
}