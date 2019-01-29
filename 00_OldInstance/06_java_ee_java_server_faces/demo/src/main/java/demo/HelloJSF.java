package demo;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloJSF")
public class HelloJSF {

    public HelloJSF() {
    }

    public String getMessage(){
        return "Hello jsf";
    }
}
