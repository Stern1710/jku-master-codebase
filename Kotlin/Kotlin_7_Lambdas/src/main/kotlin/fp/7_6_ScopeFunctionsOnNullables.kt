package fp

fun main() {

    persons.find { it.age >= 25 }?.let {
        println("Found: $it")
    }

    persons.find { it.age >= 30 }?.apply {
        println("Found adult: $this")
    }?:Person("NN", "MM", 30).run {
        println("Created: $this")
    }

    persons.find { it.age >= 60 }?.apply {
        println("Found senior: $this")
    }?: persons.find { it.age >= 30 }?.apply {
        println("Found adult: $this")
    }?: persons.find { it.age >= 20 }?.apply {
        println("Found youth: $this")
    }?: Person("NN", "MM", 30).run {
        println("Created: $this")
    }


}