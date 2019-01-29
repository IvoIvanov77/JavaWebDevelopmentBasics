package repository;

import data.Cat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CatRepository implements Repository<Cat>{
    private Map<String, Cat> cats;

    public CatRepository() {
        this.cats = new HashMap<>();
    }

    @Override
    public boolean add(Cat cat){
        if(this.cats.containsKey(cat.getName())){
            return false;
        }
        this.cats.put(cat.getName(), cat);
        return true;
    }

    @Override
    public Cat get(String name){
        return this.cats.get(name);
    }

    @Override
    public Collection<Cat> getAll(){
        return this.cats.values();
    }

    @Override
    public boolean remove(String name){
        if(this.cats.containsKey(name)){
            return false;
        }
        this.cats.remove(name);
        return true;
    }
}
