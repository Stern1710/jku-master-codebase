package sternbauer.eu.aoc

import java.io.File

/*
    AOC2 utilizes
    * Lambdas (at leas I hope I got it right), for example when converting the file input into instructions
    * data class for the Instruction
    * Extension method for File to automatically transform the read-in elements to Sliding Window data classes

    Moreover, I found it fun to use an infix function to update my submarine with the instructions. Really fun!
    (Also overriding toString and harnessing the power of string interpolation and omitting brackets :)
 */

const val fileAOC2 = "./data/aoc2.txt"

class Submarine(var depth: Int = 0, var horizontal: Int = 0) {
    infix fun updateWith(instruction: Instruction) {
        depth += instruction.depthGain
        horizontal += instruction.horizontalGain
        println(toString())
    }

    override fun toString() = "Submarine is at position $horizontal in a depth of $depth"
}

/* Data class */
data class Instruction(val depthGain: Int = 0, val horizontalGain: Int = 0)

fun File.readInstructions(): List<Instruction> {
    return readLines()
        .asSequence() //IntelliJ said performance reasons
        .map { it.split(" ") }
        .filter { it.size >= 2 }
        .map {
            with(it[0].trim() to it[1].toInt()) {
                when (first) {
                    "forward" -> Instruction(horizontalGain = second)
                    "down" -> Instruction(depthGain = second)
                    "up" -> Instruction(depthGain = second * -1)
                    else -> Instruction()//Does nothing, i.e. only adds the neutral 0 element when updating a submarine
                }
            }
        }.toList()
}

fun main() {
    val sub = Submarine()
    File(fileAOC2).readInstructions().forEach { sub updateWith it }
    println("\nFinal position: $sub")
}