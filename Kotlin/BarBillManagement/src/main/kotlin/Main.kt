import javafx.stage.Stage
import tornadofx.*
import views.MyView

class MyApp : App(MyView::class) {
    override fun start(stage: Stage) {
        with(stage) {
            minWidth = 600.0
            minHeight = 400.0
            super.start(this)
        }
    }
}

fun main(args: Array<String>) = launch<MyApp>(args)