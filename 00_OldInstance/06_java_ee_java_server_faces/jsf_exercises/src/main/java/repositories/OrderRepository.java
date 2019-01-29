package repositories;

import data.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository implements Repository<Order>{
    private Map<String, Order> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }

    @Override
    public boolean add(Order order){

        if(this.orders.containsKey(order.getId())){
            return false;
        }
        this.orders.put(order.getId(), order);
        return true;
    }

    @Override
    public Order get(String id){
        return this.orders.get(id);
    }

    @Override
    public Collection<Order> getAll(){
        return this.orders.values();
    }

    @Override
    public boolean remove(String id){
        if(this.orders.containsKey(id)){
            return false;
        }
        this.orders.remove(id);
        return true;

    }
}
