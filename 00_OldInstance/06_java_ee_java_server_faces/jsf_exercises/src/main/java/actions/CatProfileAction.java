package actions;

import data.Cat;
import services.CatService;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "catProfile")
public class CatProfileAction{
    private String catName;

    private Cat cat;

    private CatService catService;

    public CatProfileAction() {
        this.catService = new CatService();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Cat getCat() {
        if(this.cat == null){
            this.cat =this.catService.getByName(this.catName);
            this.cat.incrementViews();
        }
        return this.cat;
    }


}
