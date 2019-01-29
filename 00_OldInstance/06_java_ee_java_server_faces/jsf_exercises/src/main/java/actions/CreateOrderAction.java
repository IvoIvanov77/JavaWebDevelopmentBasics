package actions;

import data.Cat;
import data.Order;
import data.User;
import services.CatService;
import services.OrderService;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "createOrderAction")
public class CreateOrderAction {
    private String catName;

    private OrderService orderService;

    public CreateOrderAction() {
        this.orderService = new OrderService();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void create(){
       User currentUser = ApplicationUtils.getCurrentUser();
       Cat cat = (Cat) ApplicationUtils.getRepository("catRepo")
               .get(this.getCatName());
       this.orderService.create(new Order(currentUser, cat));
       if(currentUser.isAdmin()){
           ApplicationUtils.redirect("/orders/all.html");
       }else{
           ApplicationUtils.redirect("/cats/all.html");
       }

    }
}
