interface Factory<T> {
    fun create(): T
}

class House(val street: String) {
    //Could give a name, a must if I call kt from java
    companion object TotallyCool: Factory<House> {
        val agencyProvision = 3.6

        override fun create(): House = House("DefaultStreet")

    }
}

fun main() {
    //Same
    House.agencyProvision
    House.TotallyCool.agencyProvision
}