package util;

import data.User;
import repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public final class UserAuthentication {
    private UserAuthentication(){}
    
    public static User getCurrentUser(HttpServletRequest req, ServletContext sc){
        UserRepository userRepository = (UserRepository) sc.getAttribute("users");
        String username = (String) req.getSession()
                .getAttribute("username");
        if(username != null){
            return userRepository.get(username);
        }
        return null;
    }
}
