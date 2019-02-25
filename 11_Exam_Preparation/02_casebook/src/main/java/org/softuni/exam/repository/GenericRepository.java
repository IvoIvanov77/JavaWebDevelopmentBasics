package org.softuni.exam.repository;

import java.util.List;

public interface GenericRepository<Entity, Id> {
    Entity save(Entity entity);

    List<Entity> findAll();

    Entity findById(Id id);

    Entity delete(Entity user);

    Entity update(Entity user);

    Long size();
}
