package views

import CustomerBalance
import MainController
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.ContextMenu
import javafx.stage.StageStyle
import model.Bill
import model.Customer
import tornadofx.*

class MyView : View() {
    private val controller: MainController by inject(FX.defaultScope)

    private val rightElem = form {
        this.hide()
        fieldset("Edit Bill") {
            field("Amount") {
                textfield(controller.bModel.amount) { }
            }
            field("Item") {
                textfield(controller.bModel.itemb) { }
            }
            field("Comment") {
                textfield(controller.bModel.comment) { }
            }
            borderpane {
                spacing = 1.0
                left =
                    button("Discard") {
                        useMaxWidth = true
                        gridpaneConstraints {
                            columnRowIndex(0, 1)
                        }
                        setOnAction {
                            controller.bModel.rollback()
                            this@form.hide()
                        }
                    }
                right = button("Save") {
                    useMaxWidth = true
                    enableWhen(controller.bModel.dirty)
                    gridpaneConstraints {
                        columnRowIndex(1, 1)
                    }
                    setOnAction {
                        controller.bModel.commit()
                        controller.saveToFile()
                        this@form.hide()
                    }
                }
            }
        }
    }

    override val root =
        borderpane {
            setPrefSize(1200.0, 720.0)
            top = menubar {
                menu("Operations") {
                    item("New Bill") {
                        action {
                            find<NewBillView>().apply {
                                custNames.setAll(controller.getCustomerNames())
                            }.openModal(stageStyle = StageStyle.DECORATED)
                        }
                    }
                    item("Print balance") {
                        action {
                            find<BalanceFragment>().apply {
                                fragCustomers.setAll(controller.tItems.map { c ->
                                    CustomerBalance(
                                        c.personProperty.get(),
                                        c.billProperty.get().count(),
                                        c.billProperty.get().sumOf { b -> b.amountProperty.get() })
                                })
                            }.openModal(stageStyle = StageStyle.DECORATED)
                        }
                    }
                    item("Lock") {
                        action {
                            controller.saveToFile()
                            replaceWith<LockscreenView>(
                                ViewTransition.Slide(
                                    0.3.seconds,
                                    ViewTransition.Direction.RIGHT
                                )
                            )
                        }
                    }
                }

                menu("Settings") {
                    item("Unlock Pin") {
                        action {
                            openInternalWindow<SettingsPinView>()
                        }
                    }
                    item("Save data") {
                        action {
                            controller.saveToFile()
                        }
                    }
                }
            }

            left = tableview(controller.tItems) {
                column("Name", Customer::personProperty).minWidth(150).maxWidth(250)
                smartResize()

                onUserSelect(clickCount = 1) {
                    rightElem.hide()
                    controller.cModel.item = it
                }

                contextMenu = ContextMenu().apply {
                    item("Delete Entry") {
                        action {
                            selectedItem.let {
                                controller.tItems.remove(it)
                                rightElem.hide()
                                controller.saveToFile()
                            }
                        }
                    }
                }
            }

            center = tableview(controller.cModel.bills) {
                column("Amount", Bill::amountProperty).minWidth(100).maxWidth(150)
                column("Item", Bill::itemProperty).minWidth(150).maxWidth(200)
                column("Comment", Bill::commentProperty).remainingWidth()
                smartResize()

                onUserSelect(clickCount = 1) {
                    controller.bModel.item = selectedItem
                    rightElem.show()
                }

                contextMenu = ContextMenu().apply {
                    item("Delete bill") {
                        action {
                            selectedItem.let { controller.cModel.bills.remove(it) }
                            rightElem.hide()
                            controller.saveToFile()
                        }
                    }
                }
            }

            right = rightElem
        }
}


class NewBillView : View() {
    val custNames = mutableListOf<String>().asObservable()

    private val controller: MainController by inject(FX.defaultScope)
    private val nameProperty = SimpleStringProperty("")
    private val amountProperty = SimpleDoubleProperty(0.0)
    private val itemProperty = SimpleStringProperty("")
    private val commentProperty = SimpleStringProperty("")

    override val root = form {
        fieldset("New bill", FontAwesomeIconView(FontAwesomeIcon.MONEY)) {
            field("Customer") {
                combobox(values = custNames) {
                    //makeAutocompletable() //Makes program throw errors, and not even my fault :O
                    isEditable = true
                }.bind(nameProperty)
            }
            field("Amount") {
                textfield {
                }.bind(amountProperty)
            }
            field("Item") {
                textfield().bind(itemProperty)
            }
            field("Comment") {
                textfield().bind(commentProperty)
            }
            hbox(alignment = Pos.CENTER) {
                button("Save") {
                    setOnAction {
                        controller.addBill(
                            nameProperty.get(),
                            Bill(amountProperty.get(), itemProperty.get(), commentProperty.get())
                        )
                        controller.saveToFile()
                        nameProperty.set("")
                        amountProperty.set(0.0)
                        itemProperty.set("")
                        commentProperty.set("")
                        close()
                    }
                }
            }
        }
    }
}

class BalanceFragment : Fragment() {
    val fragCustomers = mutableListOf<CustomerBalance>().asObservable()

    override val root = tableview(fragCustomers) {
        minHeight = 600.0
        column("Name", CustomerBalance::personProperty).minWidth(200).maxWidth(300)
        column("Number of bills", CustomerBalance::itemProperty).minWidth(100).maxWidth(150)
        column("Balance [€]", CustomerBalance::balanceProperty).minWidth(150).maxWidth(250)
        smartResize()
    }
}