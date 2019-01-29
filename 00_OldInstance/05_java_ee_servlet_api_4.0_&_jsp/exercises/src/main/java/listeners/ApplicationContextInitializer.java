package listeners;

import repository.CatRepository;
import repository.OrderRepository;
import repository.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("cats", new CatRepository());
        sce.getServletContext().setAttribute("users", new UserRepository());
        sce.getServletContext().setAttribute("orders", new OrderRepository());
    }
}
