package views

import SecurityController
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.Alert
import tornadofx.*

class SettingsPinView : View() {
    private val controller: SecurityController by inject(FX.defaultScope)

    private val oldPinProperty = SimpleStringProperty("")
    private val newPinProperty = SimpleStringProperty("")
    private val confirmNewPinProperty = SimpleStringProperty("")

    override val root = form {
        fieldset("Change Pin", FontAwesomeIconView(FontAwesomeIcon.LOCK)) {
            field("Old Pin") { passwordfield().bind(oldPinProperty) }
            field("New Pin") { passwordfield().bind(newPinProperty) }
            field("Confirm New Pin") { passwordfield().bind(confirmNewPinProperty) }
            hbox(alignment = Pos.CENTER) {
                button("Change") {
                    setOnAction {
                        if (!oldPinProperty.get().equals(controller.pin)) {
                            val alert = Alert(Alert.AlertType.ERROR)
                            alert.title = "Error"
                            alert.headerText = "Incorrect old pin"
                            alert.contentText = "The old pin you input was not correct."
                            alert.showAndWait()
                            return@setOnAction
                        }
                        if (!newPinProperty.get().equals(confirmNewPinProperty.get())) {
                            val alert = Alert(Alert.AlertType.ERROR)
                            alert.title = "Error"
                            alert.headerText = "Missmatch"
                            alert.contentText = "The two new pins do not equal."
                            alert.showAndWait()
                            return@setOnAction
                        }
                        controller.pin = newPinProperty.get()
                        controller.saveToFile()

                        oldPinProperty.set("")
                        newPinProperty.set("")
                        confirmNewPinProperty.set("")

                        val alert = Alert(Alert.AlertType.INFORMATION)
                        alert.title = "Changed"
                        alert.headerText = "Success"
                        alert.contentText = "The pin was changed to your desired one"
                        alert.showAndWait()

                        close()
                    }
                }
            }
        }
    }
}