package repository;

import java.util.Collection;

public interface Repository<T> {
    public boolean add(T cat);

    public T get(String uniqueValue);

    public Collection<T> getAll();

    public boolean remove(String name);
}
