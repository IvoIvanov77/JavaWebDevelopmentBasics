package javache.database.file;

import javache.database.entities.Entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvFileManager implements FileManager {

    private Path path;

    public CsvFileManager(Path path) {
        this.path = path;
    }

    @Override
    public List<String> readAll() {
        try (BufferedReader br = Files.newBufferedReader(this.path)) {
            return br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void write(Collection<? extends Entity> items) {
        try (final BufferedWriter bufferedWriter =
                     Files.newBufferedWriter(this.path)) {
            String dataToWrite = items
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n"));
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
