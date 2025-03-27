package eu.sternbauer.EtlGenerator.Generation.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Service that buffers the scripted content and writes to file
 */
@Service
public class GenerationFileService {
    private final String userHome;
    private String fileName;
    private final StringBuilder sb;

    public GenerationFileService() {
        userHome = System.getProperty("user.home");
        sb = new StringBuilder();
    }

    /**
     * Resets the buffer to zero
     */
    void reset() {
        sb.setLength(0);
    }

    /**
     * Sets the file name. A {@code .sql} at the end is added automatically.
     * @param fileName String naming the file
     */
    void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Appends content to the buffer, automatically appending a linebreak ({@code \n}).
     * @param content
     */
    void addContent(String content) {
        sb.append(content).append("\n");
    }

    /**
     * Writes the buffer content to file. It assumes the user home as its location and uses the provided file name.
     * When no name is provided, 'etl_loading' is used as the default. This function will overwrite any existing file
     * with the same name.
     * @throws IOException
     * @return String with the absolute file path
     */
    String writeOut() throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            fileName = "etl_loading";
        }
        File file = new File(userHome + "/" + fileName + ".sql");
        Files.writeString(Paths.get(file.toURI()), sb.toString(),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        return file.getAbsolutePath();
    }
}