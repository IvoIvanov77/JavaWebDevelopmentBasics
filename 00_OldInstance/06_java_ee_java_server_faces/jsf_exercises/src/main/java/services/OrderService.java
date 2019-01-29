package services;

import data.Cat;
import data.Order;
import repositories.CatRepository;
import repositories.OrderRepository;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "orderService")
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = ApplicationUtils.getRepository("orderRepo");

    }

    public void create(Order order){
        this.orderRepository.add(order);
    }


    public Order getById(String id){
        return  this.orderRepository.get(id);
    }
}
