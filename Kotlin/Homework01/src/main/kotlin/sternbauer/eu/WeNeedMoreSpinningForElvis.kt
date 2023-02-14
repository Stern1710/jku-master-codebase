package sternbauer.eu

import java.io.File

object HTMLRenderer {
    fun render(element: Element): String =
        when (element) {
            is Text -> element.text
            is TaggedTextElement -> "${element.openTag}${element.text}${element.closeTag}"
            is ContainerElement -> {
                val sb = StringBuilder()
                sb.append(element.openTag).append("\n")
                element.elements.forEach { sb.append(render(it).indentEachLine()).append("\n") }
                sb.append(element.closeTag)
                sb.toString()
            }
        }

    fun render(page: Page): String {
        val sb = StringBuilder()
        sb.append("<html>").append("\n")
        sb.append("<head>".indentEachLine()).append("\n")
        sb.append("<title>${page.title}</title>".indentEachLine(depth = 4)).append("\n")
        sb.append("</head>".indentEachLine()).append("\n")
        sb.append("<body>".indentEachLine()).append("\n")
        page.elements.forEach { sb.append(render(it).indentEachLine(depth = 4)).append("\n") }
        sb.append("</body>".indentEachLine()).append("\n")
        sb.append("</html>")
        return sb.toString()
    }
}

fun main() {
    val myPage = Page(
        "My Page",
        "Welcome to the Kotlin course".h1(),
        Div(
            "Kotlin is".p(),
            HTMLList(
                true,
                ListItem(
                    "General-purpose programming language".h3(),
                    HTMLList(
                        false,
                        ListItem(
                            "Backend, Mobile, Stand-Alone, Web, ...".text()
                        )
                    )
                ),
                ListItem(
                    "Modern, multi-paradigm".h3(),
                    HTMLList(
                        false,
                        ListItem(
                            "Object-oriented, functional programming (functions as first-class citizens, ...), etc.".text()
                        ),
                        ListItem(
                            "Statically typed but automatically inferred types".text()
                        )
                    )
                ),
                ListItem(
                    "Emphasis on conciseness / expressiveness / practicality".h3(),
                    HTMLList(
                        false,
                        ListItem(
                            "Goodbye Java boilerplate code (getter methods, setter methods, final, etc.)".text()
                        ),
                        ListItem(
                            "Common tasks should be short and easy".text()
                        ),
                        ListItem(
                            "Mistakes should be caught as early as possible".text()
                        ),
                        ListItem(
                            "But no cryptic operators as in Scala".text()
                        )
                    )
                ),
                ListItem(
                    "100% interoperable with Java".h3(),
                    HTMLList(
                        false,
                        ListItem(
                            "You have a Java project? Make it a Java/Kotlin project in minutes with 100% interop".text()
                        ),
                        ListItem(
                            "Kotlin-to-Java as well as Java-to-Kotlin calls".text()
                        ),
                        ListItem(
                            "For example, Kotlin reuses Java’s existing standard library (ArrayList, etc.) and extends it with extension functions (opposed to, e.g., Scala that uses its own list implementations)".text()
                        )
                    )
                ),
            )
        )
    )
    //I know that the path is hard-coded, but it is enough to demonstrate the functionality :)
    File("index.html").writeText(HTMLRenderer.render(myPage))
}