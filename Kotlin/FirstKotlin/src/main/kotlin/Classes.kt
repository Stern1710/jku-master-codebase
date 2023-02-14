import java.awt.Color

class Person constructor(first: String = "VeryDefault") {
    val firstName : String //Assign parameter to a property
    val lastName = "HereWeGoAgain Lastname"

    //We can have multiple init blocks
    init {
        firstName = first.uppercase()
    }
}

class Rectangle(
    val width: Int,
    val height: Int
) {
    //Public get, private set
    var color: Color? = null
        private set

    val area: Int
        //Default, and also creates the field; See this in the provided code!
        //get() = field
        get() = this.width * this.area

    var loudProp = 1
        get() {
            println("HEY")
            return field
        }
        set(value) {
            println("HO-HO")
            field = value
        }
}