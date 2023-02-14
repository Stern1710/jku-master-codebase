package fp

import java.time.LocalTime


fun main() {
    val text = StringBuilder()
        .append("programming ")
        .insert(0, "Functional ")
        .append("in ")
        .append("Java ")
        .delete(26, 33)
        .append("Kotlin")
        .toString()

    println(text)

    val endsAt =
        LocalTime.now()
            .plusHours(2)
            .plusMinutes(32)
            .plusSeconds(47)

    println(endsAt)

    val m =
        mutableListOf<String>().apply {
            for (word in text.split(" ")) {
                add(word)
            }
        }.also {
            it.add("!")
        }.let {
            it.filter {it.length > 0} .mapIndexed { i, s -> Pair(i, s) }.toMap()
        }.takeUnless {
            it.isEmpty()
        }?.let {
            it.toString()
        }?:"I have no words"

    println(m)

}