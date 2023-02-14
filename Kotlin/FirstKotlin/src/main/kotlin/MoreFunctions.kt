data class UserEasy(val nickname: String)

fun mainOld() {
    val susiUser1 = UserEasy("sunny_susi1992")
    val susiUser2 = UserEasy("sunny_susi1992")

    println(susiUser1 == susiUser2) //Equals method -> true
    println(susiUser1 === susiUser2) //Same object -> false

    print("$susiUser2 ${susiUser1.toString()}")
}

data class User(val nickname: String, val age: Int)

data class Message(val receiver: User, val sender: User, val content: String="<empty>") {
    var header: String = ""
        private set

    constructor(receiver: User, sender: User, content: String, header : String) : this(receiver, sender, content) {
        this.header = header;
    }
}

fun main() {
    val susiUser = User("Sunny_susi1992", 30)
    val ferrariUser = User("ferrari420", 21)

    val msg1 = Message(susiUser, ferrariUser, "This is a love letter")
    val msg2 = Message(susiUser, ferrariUser, "This is a love letter", "Hui Dui ^^")

    println(msg1 == msg2)

    println("${msg1.hashCode()} =?= ${msg2.hashCode()}")

    val (r,s,c) = msg1 //Deconstruct

    val answer1 = Message(ferrariUser, susiUser, "I like Fiat more, lol")
    val answer2 = answer1.copy(content = "Please do not write me again.")

    val msgs = listOf(msg1, msg2, answer1, answer2)
    msgs.forEach { println(it) }
}