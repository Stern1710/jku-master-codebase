package model

import com.gitlab.mvysny.konsumexml.Konsumer
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import org.redundent.kotlin.xml.xml
import tornadofx.ItemViewModel

data class Bill(private var amount: Double = 1.0, private var item: String = "", private var comment: String = "") {
    val amountProperty = SimpleDoubleProperty(this, "amount", amount)
    val itemProperty = SimpleStringProperty(this, "item", item)
    val commentProperty = SimpleStringProperty(this, "comment", comment)

    fun toXmlNode() = xml("bill") {
        "amount" {
            -amount.toString()
        }
        "item" {
            -item
        }
        "comment" {
            -comment
        }
    }

    companion object {
        fun xml(k: Konsumer): Bill {
            k.checkCurrent("bill")
            return Bill(k.childText("amount").toDouble(), k.childText("item"), k.childText("comment"))
        }
    }
}

class BillModel(bill: Bill) : ItemViewModel<Bill>(bill) {
    val amount = bind(Bill::amountProperty)
    val itemb = bind(Bill::itemProperty)
    val comment = bind(Bill::commentProperty)
}