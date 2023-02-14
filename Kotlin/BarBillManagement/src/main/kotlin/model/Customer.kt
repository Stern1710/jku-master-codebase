package model

import com.gitlab.mvysny.konsumexml.Konsumer
import javafx.beans.property.SimpleListProperty
import org.redundent.kotlin.xml.Node
import org.redundent.kotlin.xml.xml
import tornadofx.ItemViewModel
import tornadofx.asObservable
import tornadofx.toProperty

data class Customer(private var person: String = "", private val bills: MutableList<Bill> = mutableListOf()) {
    val personProperty = person.toProperty()
    val billProperty = SimpleListProperty(this, "bills", bills.asObservable())

    fun toXmlNode(): Node {
        val people = xml("customer") {
            "person" {
                -person
            }
            "bills" {
                for (bill in bills) {
                    addNode(bill.toXmlNode())
                }
            }
        }
        return people
    }

    companion object {
        fun xml(k: Konsumer): Customer {
            k.checkCurrent("customer")
            return Customer(k.childText("person"),
                k.child("bills") { children("bill") { Bill.xml(this) } } as MutableList<Bill>)
        }
    }
}

class CustomerModel(customer: Customer) : ItemViewModel<Customer>(customer) {
    val bills = bind(Customer::billProperty)
}

