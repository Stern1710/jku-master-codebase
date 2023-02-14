class Car(val type: String, val price: Int) {
    private val tires = 4

    //Nested class, this is not an inner class
    //This is a self-regulated class, independent of a Car
    class Shop(val type: String, val owner: String) {
        fun buy(car: Car) {
            if (car.type != this.type) {
                println("We do not sell this brand here, sorry")
            } else {
                println("Thanks for ${car.price} to buy ${car.tires} tires and some HEAVY metal")
            }
        }
    }
}

//As there are 2 values called name, the this@Game is needed to access
class Game(val name: String) {
    inner class Card(val name: String) {
        override fun toString(): String {
            return "Card ${this.name} of Game ${this@Game.name}"
        }
    }

    fun createCard(name: String) = Card(name)

    class FakeInnerCard(val gameThis : Game, val cardName: String) {
        override fun toString(): String {
            return "Card ${this.cardName} of Game ${gameThis.name}"
        }
    }

    fun createFakeInnerCard(name: String) = FakeInnerCard(this, name)
}

fun main() {
    val car = Car("Toyota Yaris", price = 805)
    val shop = Car.Shop("Madza", "Mr. M")

    val game = Game("Dominion")
    val card = game.Card("Village")
    val card2 = game.createCard("Market")

    println(card)
    println(card2)
}