package servlets.orders;

import data.Cat;
import data.Order;
import data.User;
import repository.CatRepository;
import repository.OrderRepository;
import util.UserAuthentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/create")
public class OrderCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(user == null){
            resp.sendRedirect("/users/login");
            return;
        }
        OrderRepository orderRepository = (OrderRepository) this.getServletContext()
                .getAttribute("orders");
        CatRepository catRepository = (CatRepository) this.getServletContext()
                .getAttribute("cats");
        String catName = req.getParameter("cat");
        Cat cat = catRepository.get(catName);

        orderRepository.add(new Order(user, cat));

        resp.sendRedirect("/cats/all");
    }


}
