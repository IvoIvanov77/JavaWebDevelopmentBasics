package actions;

import data.Cat;
import repositories.CatRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "allCats")
public class AllCatsAction {

    public List<Cat> getSortedCats(){
        CatRepository catRepository = (CatRepository) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getApplicationMap()
                .get("catRepo");

        return catRepository.getAll()
                .stream()
                .sorted(Comparator.comparingInt(Cat::getViews).reversed())
                .collect(Collectors.toList());
    }
}
