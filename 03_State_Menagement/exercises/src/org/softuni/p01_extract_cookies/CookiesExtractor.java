package org.softuni.p01_extract_cookies;

import org.softuni.http.HttpRequest;
import org.softuni.http.constans.HTTPConstants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CookiesExtractor {

    private Map<String, String> cookies;


    public CookiesExtractor(HttpRequest httpRequest) {
        this.extractCookies(httpRequest);

    }

    private void extractCookies(HttpRequest httpRequest){
        if(!httpRequest.getHeaders().containsKey("Cookie")){
            return;
        }

        this.cookies = new HashMap<>();

        String[] cookiesStringArray = httpRequest.getHeaders().get("Cookie")
                .split(HTTPConstants.SEMICOLON_SEPARATOR);

        for (String cookieAsString : cookiesStringArray) {
            String[] keyValue = cookieAsString.split("=");
            this.cookies.putIfAbsent(keyValue[0], keyValue[1]);
        }
    }

    public Map<String, String> getCookies() {
        return Collections.unmodifiableMap(cookies);
    }
}
