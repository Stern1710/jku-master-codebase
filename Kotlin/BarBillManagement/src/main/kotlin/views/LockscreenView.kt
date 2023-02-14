package views

import SecurityController
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import tornadofx.*

class LockscreenView : View() {
    private val controller: SecurityController by inject(FX.defaultScope)
    private val pinProperty = SimpleStringProperty("")

    override val root = borderpane {
        center = form {
            fieldset("Unlock Accounting", FontAwesomeIconView(FontAwesomeIcon.LOCK)) {
                field("Pin") {
                    passwordfield(pinProperty)
                }
                button("Unlock") {
                    action {
                        if (pinProperty.get().equals(controller.pin)) {
                            pinProperty.set("")
                            replaceWith<MyView>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                        } else {
                            val alert = Alert(Alert.AlertType.WARNING)
                            alert.title = "Error"
                            alert.headerText = "Incorrect pin"
                            alert.contentText = "The provided pin was not correct"
                            alert.show()

                            pinProperty.set("")
                        }
                    }
                }
            }
        }
    }
}


