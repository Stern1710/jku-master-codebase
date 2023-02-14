package fp

fun main() {

    var ages = persons.map({ p : Person -> p.age })

    ages = persons.map({ p -> p.age })

    ages = persons.map() { p -> p.age }

    ages = persons.map { p -> p.age }

    ages = persons.map { it.age }

    var names = persons.fold("", {
            names, p -> "names ${p.firstName}"
    })

    names = persons.fold("") {
            names, p -> "names ${p.firstName}"
    }

    ages = persons.map(Person::age)

    persons.forEach(::printHello)

    val equalMueller = "Mueller"::equals

    val muellers = persons.map(Person::lastName).filter(equalMueller)
    println(muellers)

    val createPerson = ::Person
    val joe = createPerson("Joe", "Burger", 21)

    persons.forEach(fun (p: Person) {
        println(p)
    })

    countAdults(persons)

}

fun printHello(person: Person) = println("Hello ${person.firstName}")

fun countAdults(persons : List<Person>) : Int {

    var nAdults = 0
    //val action : (Person) -> Unit = { person: Person -> if (person.age >= 18) nAdults++ }
    persons.forEach { person: Person -> if (person.age >= 18) nAdults++ }
    return nAdults
}