package repositories;

import data.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Repository<User>{
    private Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    @Override
    public boolean add(User user){
        if(this.users.containsKey(user.getUsername())){
            return false;
        }
        this.users.put(user.getUsername(), user);
        return true;
    }

    @Override
    public User get(String name){
        return this.users.get(name);
    }

    @Override
    public Collection<User> getAll(){
        return this.users.values();
    }

    @Override
    public boolean remove(String name){
        if(this.users.containsKey(name)){
            return false;
        }
        this.users.remove(name);
        return true;
    }
}
