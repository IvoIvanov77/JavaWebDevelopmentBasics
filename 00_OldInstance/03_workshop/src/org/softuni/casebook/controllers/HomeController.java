package org.softuni.casebook.controllers;

import org.softuni.casebook.CasebookWebConstants;
import org.softuni.casebook.util.anotations.GetMapping;
import org.softuni.javache.http.HttpRequest;
import org.softuni.javache.http.HttpResponse;
import org.softuni.javache.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class HomeController extends BaseController {

    @GetMapping
    public byte[] index(HttpRequest httpRequest, HttpResponse httpResponse) {
        if(httpRequest.getCookies().containsKey(CasebookWebConstants.CASEBOOK_SESSION_KEY)) {
            String currentUserUsername = httpResponse.getSession()
                    .getAttributes().get("username").toString();
            Map<String, String> viewMap = new HashMap<>(){{
                put("username", currentUserUsername);
            }};
            return this.processPageRequest("/home", httpResponse, viewMap);
        }

        return this.processPageRequest("/index", httpResponse);
    }


}
