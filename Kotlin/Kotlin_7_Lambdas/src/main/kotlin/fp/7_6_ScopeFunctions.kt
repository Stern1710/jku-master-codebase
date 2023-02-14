package fp

fun main() {

    val l1 : List<String> =
        mutableListOf<String>().let {
            it.add("Kotlin")
            it.addAll(listOf("and", "Java"))
            it.reverse()
            it.toList()
        }

    val l2 : List<String> =
        mutableListOf<String>().run {
            this.add("Kotlin")
            this.addAll(listOf("and", "Java"))
            this.reverse()
            this.toList()
        }

    val l3: List<String> =
        with(mutableListOf<String>()) {
            this.add("Kotlin")
            this.addAll(listOf("and", "Java"))
            this.reverse()
            this.toList()
        }

    val l4: MutableList<String> =
        mutableListOf<String>().apply {
            this.add("Kotlin")
            this.addAll(listOf("and", "Java"))
            this.reverse()
        }

    val l5: List<String> =
        mutableListOf<String>().also {
            it.add("Kotlin")
            it.addAll(listOf("and", "Java"))
            it.reverse()
        } .toList()

    val l6: MutableList<String> =
        mutableListOf<String>().apply {
            this.add("Hallo")
            this.add("Kotlin")
        } .takeIf { it.contains("Kotlin") } ?: mutableListOf()


    println(l1)
    println(l2)
    println(l3)
    println(l4)
    println(l5)
    println(l6)
}