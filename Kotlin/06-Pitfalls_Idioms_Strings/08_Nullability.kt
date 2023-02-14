fun main() {
    var emptyInTheBeginning1 : List<String>? = null
    var emptyInTheBeginning2 : List<String?>? = null

    val list1 = listOf("Markus", null, "likes", "Kotlin") // Type: List<String?>
    emptyInTheBeginning2 = list1
    processListWithNullableString(list1)

    // filterNotNull can be applied on collections of nullable types
    // fun <T> Iterable<T?>.filterNotNull(): List<T>
    processListWitNonNullableStrings(list1.filterNotNull())
    emptyInTheBeginning1 = list1.filterNotNull()
}

fun processNonNullableListWitNonNullableStrings(list: List<String>) {
    for (str in list) {
        println(str.length)
    }
}

fun processNonNullableListWithNullableString(list: List<String?>) {
    for (str in list) {
        // Items of the list may be null
        if(str != null) {
            println(str.length)
        }
    }
}

fun processNullableListWithNullableString(list: List<String?>?) {
    // List itself may be null
    if(list != null) {
        for (str in list) {
            // Items of the list may be null
            if(str != null) {
                println(str.length)
            }
        }
    }
}