package org.softuni.p02_create_classes;

import org.softuni.http.HttpRequestImpl;
import org.softuni.http.constans.HTTPConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestImplExtended
        extends HttpRequestImpl
        implements CookiesReadableRequest {

    private Map<String, HttpCookie> cookies;

    public HttpRequestImplExtended(String requestContent) {
        super(requestContent);
        this.initCookies();
    }

    private void initCookies() {
        if(!this.getHeaders().containsKey("Cookie")){
            return;
        }

        this.cookies = new HashMap<>();

        String[] cookiesStringArray = this.getHeaders().get("Cookie")
                .split(HTTPConstants.SEMICOLON_SEPARATOR);

        for (String cookieAsString : cookiesStringArray) {
            String[] keyValue = cookieAsString.split("=");
            HttpCookie cookie = new HttpCookieImpl(keyValue[0], keyValue[1]);
            this.cookies.putIfAbsent(cookie.getName(), cookie);
        }
    }

    @Override
    public Map<String, HttpCookie> getCookies() {
        return Collections.unmodifiableMap(this.cookies);
    }


}
