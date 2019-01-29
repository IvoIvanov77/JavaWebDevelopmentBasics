package actions;

import data.Cat;
import data.User;
import services.CatService;
import services.UserService;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "createCatAction")
public class CreateCatAction {
    private String name;

    private String breed;

    private String color;

    private int numberOfLegs;

    private CatService catService;

    public CreateCatAction() {
        this.catService =new CatService();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public void create(){
       this.catService.create(new Cat(
               this.getName(),
               this.getBreed(),
               this.getColor(),
               this.getNumberOfLegs()
       ));
       ApplicationUtils.redirect("/cats/all.html");
    }
}
