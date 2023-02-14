import tornadofx.toProperty

data class CustomerBalance(private val person: String, private val itemCount: Int, private val balance: Double) {
    val personProperty = person.toProperty()
    val itemProperty = itemCount.toProperty()
    val balanceProperty = balance.toProperty()
}
