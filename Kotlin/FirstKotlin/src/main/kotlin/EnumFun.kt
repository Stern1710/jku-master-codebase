enum class MathSymbols(val letter: String) {
    SUM("Sigma"),
    PRODUCT("Pi");

    override fun toString(): String {
        return super.toString() + ", visualized as $letter"
    }
}

enum class Sound {
    CRASH {
        override fun play() {
            println("Kawoooooosh")
        }
    },
    JUMP {
        override fun play() {
            println("huiiiiii")
        }
    };

    abstract fun play() //Without this, we cannot be sure that Sound HAS a Crahs sound, therefore we cannot access it
}

fun main() {
    println(MathSymbols.SUM)
    println(MathSymbols.PRODUCT)

    Sound.CRASH.play()
    Sound.JUMP.play()

    //Implicit : Any() inheritance
    val helloWorld = object  {
        val hello = "hello"
        val world = "world"

        override fun toString(): String {
            return "$hello $world"
        }

        fun print() {
            println(this.toString())
        }
    }
    //As helloWorld is defined here, we can access the print method here
    helloWorld.print()
}