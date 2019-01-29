package repository;

import data.Cat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CatRepository {
    private Map<String, Cat> cats;

    public CatRepository() {
        this.cats = new HashMap<>();
    }

    public boolean add(Cat cat){
        if(this.cats.containsKey(cat.getName())){
            return false;
        }
        this.cats.put(cat.getName(), cat);
        return true;
    }

    public Cat get(String name){
        return this.cats.get(name);
    }

    public Collection<Cat> getAll(){
        return this.cats.values();
    }

    public boolean remove(String name){
        if(this.cats.containsKey(name)){
            return false;
        }
        this.cats.remove(name);
        return true;
    }
}
