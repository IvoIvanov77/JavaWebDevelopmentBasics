package actions;

import data.Cat;
import data.Order;
import repositories.CatRepository;
import repositories.OrderRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "allOrders")
public class AllOrdersAction {

    public List<Order> getSortedOrders(){
        OrderRepository orderRepository = (OrderRepository) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getApplicationMap()
                .get("orderRepo");

        return orderRepository.getAll()
                .stream()
                .sorted(Comparator.comparing(Order::getMadeOn).reversed())
                .collect(Collectors.toList());
    }
}
