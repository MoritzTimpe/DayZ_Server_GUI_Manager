package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class Mod{

    private final String name;
    private final Path path;
    private File keyFile;

    public Mod(Path path){
        this.path = path;
        this.name = path.getFileName().toString();

        resolveKeyFile(path);
    }

    private void resolveKeyFile(Path path) {
        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(path.resolve("Keys"), "*.bikey")) {

            Iterator<Path> it = stream.iterator();
            if (!it.hasNext()) {
                throw new IllegalStateException("No key file found");
            }

            this.keyFile = it.next().toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // GETTER

    public String getName() {
        return this.name;
    }

    public Path getPath() {
        return this.path;
    }

    public File getKeyFile() {
        return this.keyFile;
    }
}
