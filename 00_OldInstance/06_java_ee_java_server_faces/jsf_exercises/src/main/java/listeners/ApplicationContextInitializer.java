package listeners;

import data.Cat;
import repositories.CatRepository;
import repositories.OrderRepository;
import repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(eager=true)
@ApplicationScoped
public class ApplicationContextInitializer  {

    @PostConstruct
    public void init() {
        System.out.println("init");
        CatRepository catRepository = new CatRepository();
        catRepository.add(new Cat("ivo", "long", "red", 4));
        catRepository.add(new Cat("pesho", "long", "red", 4));
        FacesContext.getCurrentInstance()
                .getExternalContext().getApplicationMap()
                .put("catRepo", catRepository);
        FacesContext.getCurrentInstance()
                .getExternalContext().getApplicationMap()
                .put("userRepo", new UserRepository());
        FacesContext.getCurrentInstance()
                .getExternalContext().getApplicationMap()
                .put("orderRepo", new OrderRepository());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }
}
