package javache.database.file;

import javache.database.entities.Entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public interface FileManager {

    List<String> readAll();

    void write(Collection<? extends Entity> items);

}
