package org.softuni.casebook;

import org.softuni.casebook.controllers.BaseController;
import org.softuni.casebook.controllers.HomeController;
import org.softuni.casebook.controllers.ResourceController;
import org.softuni.casebook.controllers.UserController;
import org.softuni.casebook.util.ControllerActionPair;
import org.softuni.casebook.util.anotations.GetMapping;
import org.softuni.casebook.util.anotations.PostMapping;
import org.softuni.javache.api.RequestHandler;
import org.softuni.javache.http.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CasebookApplication implements RequestHandler {
    private boolean intercepted;

    private HttpRequest httpRequest;

    private HttpResponse httpResponse;

    private HttpSessionStorage sessionStorage;

    private Set<BaseController> controllers;

//    private Map<String, ControllerActionPair> getRequestRoutingMap;
//
//    private Map<String, ControllerActionPair> postRequestRoutingMap;

    public CasebookApplication(HttpSessionStorage sessionStorage) {
        this.intercepted = false;
        this.sessionStorage = sessionStorage;
        this.initialiseControllersSet();
//        this.initializeGetRoutingMap();
//        this.initializePostRoutingMap();
    }

    private void initialiseControllersSet() {
        this.controllers = new HashSet<>(){{
            add(new HomeController());
            add(new UserController());
            add(new ResourceController());
        }};
    }



    @Override
    public byte[] handleRequest(String requestContent) {
        this.httpRequest = new HttpRequestImpl(requestContent);
        this.httpResponse = new HttpResponseImpl();

        if(this.hasLoggedInUser()){
            String clientSessionId = this.httpRequest.getCookies()
                    .get(CasebookWebConstants.CASEBOOK_SESSION_KEY)
                    .getValue();
            HttpSession currentClientSession = this.sessionStorage.getById(clientSessionId);
            this.httpResponse.setSession(currentClientSession);
        }else {
            this.httpRequest.getCookies().remove(CasebookWebConstants.CASEBOOK_SESSION_KEY);
        }

        byte[] result = null;

        if (this.httpRequest.getMethod().equals("GET")) {
            result = this.processGetRequest();
        } else if (this.httpRequest.getMethod().equals("POST")) {
            result = this.processPostRequest();
        }

        HttpSession session = this.httpResponse.getSession();

        if(session != null && this.sessionStorage.getById(session.getId()) == null){
            this.sessionStorage.addSession(session);
        }

        this.sessionStorage.refreshSessions();

        this.intercepted = true;
        return result;
    }

    @Override
    public boolean hasIntercepted() {
        return this.intercepted;
    }

    private byte[] processGetRequest() {
        String requestUrl = this.httpRequest.getRequestUrl();

        for (BaseController controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if(method.isAnnotationPresent(GetMapping.class)){
                    if(method.getAnnotation(GetMapping.class).route().equals(requestUrl)){
                        try {
                            return (byte[]) method.invoke(controller, this.httpRequest, this.httpResponse);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

//        if (this.getRequestRoutingMap.containsKey(requestUrl)) {
//            ControllerActionPair cap =
//                    this.getRequestRoutingMap
//                            .get(requestUrl);
//
//            try {
//                return (byte[]) cap
//                        .getAction()
//                        .invoke(cap.getController(),
//                                this.httpRequest,
//                                this.httpResponse);
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }

        return new ResourceController().processResourceRequest(this.httpRequest, this.httpResponse);
    }

    private byte[] processPostRequest() {
        String requestUrl = this.httpRequest.getRequestUrl();

        for (BaseController controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if(method.isAnnotationPresent(PostMapping.class)){
                    if(method.getAnnotation(PostMapping.class).route().equals(requestUrl)){
                        try {
                            return (byte[]) method.invoke(controller, this.httpRequest, this.httpResponse);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

//        if (this.postRequestRoutingMap.containsKey(requestUrl)) {
//            ControllerActionPair cap =
//                    this.postRequestRoutingMap
//                            .get(requestUrl);
//
//            try {
//                return (byte[]) cap
//                        .getAction()
//                        .invoke(cap.getController(),
//                                this.httpRequest,
//                                this.httpResponse);
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//
        return new ResourceController().processResourceRequest(this.httpRequest, this.httpResponse);
    }

    private boolean hasLoggedInUser(){
        if(this.httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)){
            String sessionId = this.httpRequest.getCookies()
                    .get(CasebookWebConstants.CASEBOOK_SESSION_KEY).getValue();
            return this.sessionStorage.getById(sessionId) != null;
        }
        return false;
    }

    //    private void initializeGetRoutingMap() {
//        try {
//            this.getRequestRoutingMap = new HashMap<>() {{
//                put("/", new ControllerActionPair(
//                        new HomeController(),
//                        HomeController.class.getDeclaredMethod(
//                                "index",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//                put("/login", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "login",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//                put("/register", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "register",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//                put("/users", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "users",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//                put("/friends", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "friends",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//                put("/addFriend", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "addFriend",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//            }};
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void initializePostRoutingMap() {
//        try {
//            this.postRequestRoutingMap = new HashMap<>() {{
//                put("/register", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "registerPost",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//                put("/login", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "loginPost",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//
//                put("/logout", new ControllerActionPair(
//                        new UserController(),
//                        UserController.class.getDeclaredMethod(
//                                "logout",
//                                HttpRequest.class, HttpResponse.class)
//                ));
//            }};
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }
}
