import data.Cat;
import repositories.CatRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ManagedBean(name = "app")
public class App {

    public List<Cat> getCats(){
        CatRepository catRepository = (CatRepository) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getApplicationMap()
                .get("catRepo");

        return new ArrayList<>(catRepository.getAll());
    }
}
