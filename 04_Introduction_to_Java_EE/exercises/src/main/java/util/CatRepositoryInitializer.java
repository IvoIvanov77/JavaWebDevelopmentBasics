package util;

import repository.CatRepository;

import javax.servlet.ServletContext;

public class CatRepositoryInitializer {


    private static final String ALL_CATS = "allCats";

    private CatRepositoryInitializer() {

    }

    public static void initialize(ServletContext servletContext){
        if(servletContext.getAttribute(ALL_CATS) == null){
            servletContext.setAttribute(ALL_CATS, new CatRepository());
        }

    }
}
