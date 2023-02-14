import com.gitlab.mvysny.konsumexml.konsumeXml
import model.Bill
import model.BillModel
import model.Customer
import model.CustomerModel
import org.redundent.kotlin.xml.xml
import tornadofx.Controller
import tornadofx.asObservable
import java.io.File

class MainController : Controller() {
    val tItems = mutableListOf<Customer>().asObservable()
    val cModel = CustomerModel(Customer())
    val bModel = BillModel(Bill())

    val converter: FileStorage<List<Customer>>
    private var toXmlString = { mylist: List<Customer> ->
        xml("customers") {
            for (cust in mylist) {
                addNode(cust.toXmlNode())
            }
        }.toString(prettyFormat = true)
    }

    private var fromXmlString = { input: String -> mutableListOf<Customer>() }

    init {
        converter = FileStorage(toXmlString, fromXmlString, "barbill.xml")
        val f: File = converter.getFilePath()
        if (f.exists()) {
            tItems.setAll(f.konsumeXml().use { k ->
                k.child("customers") {
                    children("customer") { Customer.xml(this) }
                }
            })
        }
    }

    fun addBill(customerName: String, bill: Bill) {
        var customer = tItems.find { it.personProperty.get() == customerName }
        if (customer == null) {
            customer = Customer(customerName)
            tItems.add(customer)
        }
        customer.billProperty.add(bill)
        cModel.item = customer
    }

    fun getCustomerNames(): List<String> = tItems.map { it.personProperty.get() }.toList()

    fun saveToFile() {
        for (customer in tItems) {
            println(customer)
        }
        converter.writeToFile(tItems)
    }
}

class SecurityController : Controller() {
    var pin: String
    val converter: FileStorage<String>

    private val toOutput = { pin: String -> pin }
    private val fromInput = { input: String -> input }

    init {
        converter = FileStorage(toOutput, fromInput, "pin.txt")
        pin = converter.readFromFile() ?: ""
    }

    fun saveToFile() {
        println(toOutput(pin))
        converter.writeToFile(pin)
    }
}