package fp

data class Person(val firstName: String, val lastName: String, val age: Int) {
    override fun toString(): String {
        return "$firstName $lastName, $age"
    }
}

val men = listOf(
    Person("Hans", "Maier", 25),
    Person("Sepp", "Mueller", 27),
    Person("Fritz", "Mueller", 27)
)

val women = listOf(
    Person("Anna", "Berger", 26),
    Person("Gerda", "Hofer", 23)
)

val persons = men + women




