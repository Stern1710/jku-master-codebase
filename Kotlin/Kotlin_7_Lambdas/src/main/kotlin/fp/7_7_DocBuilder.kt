    package fp

internal val nl = "\n"
internal val nlnl = "\n\n"

fun document(init: Doc.() -> Unit) : Doc {
    val doc = Doc()
    doc.init()
    return doc
}

interface Renderable {
    fun render() : String {
        val b = StringBuilder()
        render(b)
        return b.toString()
    }
    fun render(b: StringBuilder) : Unit
}

class Doc : Renderable {
    internal var title : String = ""
    internal var authors : String = ""
    internal var affiliation : String = ""
    internal val sections = arrayListOf<Section>()

    private var sectNr = 1

    fun section(heading: String, init: Section.() -> Unit) {
        val section = Section(listOf(sectNr++), heading)
        sections.add(section)
        section.init()
    }

    override fun render(b: StringBuilder) {
        b.append(nl + title + nlnl)
        b.append(authors + nl)
        b.append(affiliation + nlnl)
        for (sect in sections) {
            b.append(nl)
            sect.render(b)
        }
    }
}

abstract class Element : Renderable

class Section(val nrs : List<Int>, val heading : String) : Element() {
    internal val elements = ArrayList<Element>()

    private var subSectNr = 1

    fun subSection(heading: String, init: Section.() -> Unit) {
        val subSectNrs = mutableListOf<Int>()
        subSectNrs.addAll(nrs)
        subSectNrs.add(subSectNr++)
        val section = Section(subSectNrs, heading)
        elements.add(section)
        section.init()
    }

    fun paragraph(init: Paragraph.() -> Unit) {
        val paragraph = Paragraph()
        elements.add(paragraph)
        paragraph.init()
    }

    override fun render(b: StringBuilder)  {

        b.append("${nrs.joinToString(separator = ".")} $heading \n\n")
        for (elem in elements) {
            elem.render(b)
        }
    }
}

class Paragraph : Element() {
    internal val lines = arrayListOf<String>()

    fun line(text: String) {
        lines.add(text)
    }

    operator fun String.unaryPlus() {
        lines.add(this)
    }

    override fun render(b: StringBuilder) {
        for (line in lines) {
            b.append(line + nl)
        }
        b.append(nl)
    }
}

fun main() {
    val doc =
        document {
            title = "Programming in Kotlin"
            authors = "Markus, Herbert, Alex"
            affiliation = "Institute for System Software"
            section("Basics") {
                subSection("Classes") {
                    paragraph {
                        +"Classes in Kotlin can be concrete and abstract."
                        +"An abstract class is declared with modifier abstract. "
                    }
                    paragraph {
                        +"A special type of class are data classes."
                        +"Data classes are extremely useful. "
                    }
                }
                subSection("Functions") {
                    paragraph {
                        +"In Kotlin functions can be top level"
                        +"or defined in a class. "
                    }
                    paragraph {
                        +"Extension functions allow adding functions to existing classes."
                        +"In this way one can, for example, define new functions for strings."
                    }
                }
            }
            section("Lambdas and Higher-order Functions") {
                subSection("Lambdas") {
                    paragraph {
                        +"Lambdas created function objects. "
                        +"Function types FunctionN are the types for function objects. "
                    }
                }
                subSection("Higher-order functions") {
                    paragraph {
                        +"Higher-order functions are functions"
                        +"with functions as parameters. "
                        +"With extension functions higher-order functions "
                        +"can be added to collections. "
                    }
                    subSection("Higher-order functions for collections") {
                        paragraph {
                            +"Higher-order functions for collections are defined by extension functions."
                            +"For example, functions are map, filter, etc. "
                        }
                    }
                }
            }
            section("Conclusion") {
                paragraph {
                    +"Kotlin is a fine language."
                }
            }
        }
    val text = doc.render()
    println(text)
}