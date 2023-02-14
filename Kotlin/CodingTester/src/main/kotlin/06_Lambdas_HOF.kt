import kotlin.random.Random

data class Student(val name: String, val year: Int, val age: Int)

val people = (1..20).map {Student("P$it", Random.nextInt(1, 9), Random.nextInt(18, 30))}.toList()

val numbers = (1..100).toList()

fun main() {
    people.map(Student::age).forEach {print("$it, ")}
    println()

    people.map(Student::year).filter { it > 4 }.forEach { print("$it, ")}
    println()

    people.map(Student::year).filter { it > 4 }.sorted().forEach { print("$it, ")}
    println()

    people.map(Student::year).filter { it > 4 }.groupBy { it }.forEach { print("$it, ")}
    println()

    people.map(Student::year).groupBy { it }.forEach { print("$it, ")}
    println()

    people.map(Student::year).groupBy { it }.filter { it.value.size > 2 }.forEach { print("$it, ")}
    println()

    people.forEach { getInt(it) { it.age } }
    println()

    people.forEach { it.anotherInt { it.age } }

    people.joinToString{it.toString()}.forEach(::print)
    println("-------------------")

    numbers.asSequence().map { it*it }.filter { it%2 != 0 }.take(4).forEach(::println)
}

fun getInt (s: Student, item: Student.() -> Int = {1}) {
    print("Int: ${s.item()}, ")
}

fun <T> T.anotherInt(fn: T.() -> Int) {
    print("Ano: ${fn()}, ")
}
