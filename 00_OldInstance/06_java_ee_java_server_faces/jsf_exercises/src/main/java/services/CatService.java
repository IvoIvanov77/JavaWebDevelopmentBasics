package services;

import data.Cat;
import data.User;
import repositories.CatRepository;
import repositories.UserRepository;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "catService")
public class CatService {

    private CatRepository catRepository;

    public CatService() {
        this.catRepository = ApplicationUtils.getRepository("catRepo");

    }

    public void create(Cat cat){
        this.catRepository.add(cat);
    }


    public Cat getByName(String name){
        return  this.catRepository.get(name);
    }
}
