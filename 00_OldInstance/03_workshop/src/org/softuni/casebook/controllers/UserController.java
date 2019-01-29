package org.softuni.casebook.controllers;

import org.softuni.casebook.CasebookWebConstants;
import org.softuni.casebook.util.anotations.GetMapping;
import org.softuni.casebook.util.anotations.PostMapping;
import org.softuni.database.entities.User;
import org.softuni.database.repositories.BaseRepository;
import org.softuni.database.repositories.UserRepository;
import org.softuni.javache.http.HttpRequest;
import org.softuni.javache.http.HttpResponse;
import org.softuni.javache.http.HttpSession;
import org.softuni.javache.http.HttpSessionImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends BaseController {
    private UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }


    @GetMapping(route = "/login")
    public byte[] login(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0], "/", httpResponse);
        }

        return this.processPageRequest("/login", httpResponse);
    }

    @GetMapping(route = "/register")
    public byte[] register(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0], "/", httpResponse);
        }

        return this.processPageRequest("/register", httpResponse);
    }

    @PostMapping(route = "/register")
    public byte[] registerPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0], "/", httpResponse);
        }

        Map<String, String> requestBody = httpRequest.getBodyParameters();
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        String confirmPassword = requestBody.get("confirmPassword");

        if(!password.equals(confirmPassword)){
            return this.badRequest("Passwords do not match".getBytes(), httpResponse);
        }

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        this.userRepository.saveUser(user);

        return this.redirect(String.format("Successful created user - %s ", user.getUsername()).getBytes(),
                "/login",
                httpResponse);
    }

    @PostMapping(route = "/login")
    public byte[] loginPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0], "/", httpResponse);
        }

        Map<String, String> requestBody = httpRequest.getBodyParameters();
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        User user = this.userRepository.findByUsername(username);

        if(user == null || !user.getPassword().equals(password)){
            return this.badRequest("Wrong username or password".getBytes(), httpResponse);
        }

        HttpSession session = new HttpSessionImpl();
        session.addAttribute("username", username);
        httpResponse.setSession(session);
        httpResponse.addCookie(CasebookWebConstants.CASEBOOK_SESSION_KEY, session.getId());

        return this.redirect(String.format("Successful logged in user - %s ", user.getUsername()).getBytes(),
                "/",
                httpResponse);
    }

    @PostMapping(route = "/logout")
    public byte[] logout(HttpRequest httpRequest, HttpResponse httpResponse){
        HttpSession currentClientSession = httpResponse.getSession();

        if(currentClientSession != null){
            currentClientSession.invalidate();
        }

        httpResponse.addCookie(CasebookWebConstants.CASEBOOK_SESSION_KEY,
                CasebookWebConstants.COOKIE_DELETE);

        return this.redirect("Successful logged out".getBytes(),
                "/",
                httpResponse);
    }

    @GetMapping(route = "/users")
    public byte[] users(HttpRequest httpRequest, HttpResponse httpResponse) {
        if(!httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0],"/login", httpResponse);
        }

        String currentUserUsername = httpResponse.getSession()
                .getAttributes().get("username").toString();
        User currentUser = this.userRepository.findByUsername(currentUserUsername);
        System.out.println(currentUser.getFriends());

        StringBuilder result = new StringBuilder();

        this.userRepository.findAll()
                .stream()
                .filter(user -> !user.getUsername().equals(currentUserUsername) &&
                        currentUser.getFriends()
                                .stream()
                                .noneMatch(u -> u.getUsername().equals(user.getUsername())))
                .forEach(user -> result.append(String.format("<h4>%s" +
                        "<a class=\"add-friend-btn btn btn-primary mt-2\" " +
                        "href=\"/addFriend?%s=%s\">" +
                        "add friend</a>" +
                        "</h4>", user.getUsername(), "username", user.getUsername())));

        Map<String, String> viewData = new HashMap<>(){{
            put("otherUsers", result.toString());
        }};
        return this.processPageRequest("/users", httpResponse, viewData);
    }

    @GetMapping(route = "/friends")
    public byte[] friends(HttpRequest httpRequest, HttpResponse httpResponse) {
        if(!httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0],"/login", httpResponse);
        }

        String currentUserUsername = httpResponse.getSession()
                .getAttributes().get("username").toString();

        StringBuilder result = new StringBuilder();

        this.userRepository.findByUsername(currentUserUsername)
                .getFriends()
                .forEach(user -> {
                    result.append(String.format("<h4>%s" +
                            "<a class=\"add-friend-btn btn btn-danger mt-2\" " +
                            "href=\"/remove?%s=%s\">" +
                            "remove friend</a>" +
                            "</h4>", user.getUsername(), "friend", user.getUsername()));
                });

        Map<String, String> viewData = new HashMap<>(){{
            put("friends", result.toString());
        }};
        return this.processPageRequest("/friends", httpResponse, viewData);
    }

    @GetMapping(route = "/addFriend")
    public byte[] addFriend(HttpRequest httpRequest, HttpResponse httpResponse) {
        if(!httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0],"/login", httpResponse);
        }

        String currentUserUsername = httpResponse.getSession()
                .getAttributes().get("username").toString();
        String friendUsername = httpRequest.getQueryParameters().get("username");
        User user = this.userRepository.findByUsername(currentUserUsername);
        User friend = this.userRepository.findByUsername(friendUsername);

        this.userRepository.addFriend(user, friend);

        return this.redirect(new byte[0], "/users", httpResponse);
    }

    @GetMapping(route = "/remove")
    public byte[] removeFriend(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (!httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            return this.redirect(new byte[0], "/login", httpResponse);
        }

        String username = httpResponse.getSession().getAttributes().get("username").toString();
        String friendUsername = httpRequest.getQueryParameters().get("friend");

        User currentUser = this.userRepository.findByUsername(username);
        User friendUser = this.userRepository.findByUsername(friendUsername);

        this.userRepository.removeFriend(currentUser, friendUser);

        return this.redirect(
                String.format("Successfully removed friend - %s"
                        , friendUser.getUsername()).getBytes(),
                "/friends",
                httpResponse);
    }
}
