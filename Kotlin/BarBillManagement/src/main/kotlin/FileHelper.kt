import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

private const val dataDir = "./data/"

class FileStorage<T>(
    private val convertToString: (T) -> String,
    private val convertFromString: (String) -> T,
    private val fileName: String
) {
    fun readFromFile(): T? {
        val file = File("$dataDir$fileName")
        if (file.exists().not()) return null
        return convertFromString(file.readText())
    }

    fun getFilePath() = File("$dataDir$fileName")

    fun writeToFile(elem: T) {
        val file = File("$dataDir$fileName")
        if (file.exists()) file.delete()
        file.createNewFile()
        file.writeText(convertToString(elem))
    }

    companion object {
        init {
            Files.createDirectories(Paths.get(dataDir))
        }
    }
}