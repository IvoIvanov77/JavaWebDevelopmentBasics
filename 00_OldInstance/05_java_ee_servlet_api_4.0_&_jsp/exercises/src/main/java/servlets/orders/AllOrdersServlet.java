package servlets.orders;

import data.Cat;
import data.Order;
import data.User;
import enums.UserRole;
import repository.CatRepository;
import repository.OrderRepository;
import util.UserAuthentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@WebServlet("/orders/all")
public class AllOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        CatRepositoryInitializer.initialize(this.getServletContext());

        User user = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(user == null){
            resp.sendRedirect("/users/login");
            return;
        }
        if(!user.getUserRole().equals(UserRole.ADMIN)){
            resp.sendRedirect("/");
            return;
        }

        OrderRepository orderRepository = (OrderRepository) this.getServletContext()
                .getAttribute("orders");

        Collection<Order> allOrders = orderRepository.getAll();
        List<Order> sortedOrders = new ArrayList<>(allOrders);
        sortedOrders.sort(Comparator.comparing(Order::getMadeOn).reversed());

        req.setAttribute("allOrders", sortedOrders);

        req.getRequestDispatcher("allOrders.jsp").forward(req, resp);
    }
}
