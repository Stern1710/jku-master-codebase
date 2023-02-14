package sternbauer.eu

/* --- Interfaces --- */
sealed interface Element //Does not even need curly braces :O

sealed interface TaggedElement : Element {
    val tag: String //Implicitly abstract as not default implementation is given
    val openTag: String
        get() = "<$tag>" //I so love string interpolation
    val closeTag: String
        get() = "</$tag>"
}

sealed interface TextElement : Element {
    val text: String
}

sealed interface TaggedTextElement : TaggedElement, TextElement

sealed interface ContainerElement : TaggedElement {
    val elements: List<Element>
}

/* --- Data Classes --- */

data class Text(override val text: String) : TextElement

data class Paragraph(override val text: String) : TaggedTextElement {
    override val tag: String
        get() = "p"
}

data class Heading(val level: Int = 1, override val text: String) : TaggedTextElement {
    override val tag: String
        get() = "h$level"

    init {
        if (level !in 1..6) error("The level of the heading is invalid, only 1..6 is allowed.")
    }
}

data class Div(override val elements: List<Element>) : ContainerElement {
    override val tag: String
        get() = "div"

    constructor(vararg elements: Element) : this(elements.toList())
}
data class HTMLList(override val elements: List<ListItem>, val ordered: Boolean) : ContainerElement {
    override val tag: String
        get() = if (ordered) "ol" else "ul"

    constructor(ordered: Boolean, vararg elements: ListItem) : this(elements.toList(), ordered)
}

data class ListItem(override val elements: List<Element>) : ContainerElement {
    override val tag: String
        get() = "li"

    constructor(vararg elements: Element) : this(elements.toList())
}

data class Page(val title: String, val elements: List<Element>) {
    constructor(title: String, vararg elements: Element) : this(title, elements.toList())
}