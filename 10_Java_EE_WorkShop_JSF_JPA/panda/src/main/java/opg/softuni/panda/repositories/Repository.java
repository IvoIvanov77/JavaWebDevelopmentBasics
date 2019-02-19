package opg.softuni.panda.repositories;

import java.util.List;

public interface Repository<Id, Entity> {
    Entity findById(Id id);

    List<Entity> findAll();

    Entity save(Entity entity);

    Entity delete(Entity entity);

}
