package javache.database.repositories;

import javache.database.entities.Entity;

import java.util.Collection;

public interface Repository<T extends Entity> {

    Collection<T> getAll();

    T getById(String id);

    void add(T item);

    boolean remove(String id);

    boolean update(T item);

    boolean contains(String id);

    void flushData();

}
