package org.softuni;

import org.softuni.http.HttpRequest;
import org.softuni.http.HttpRequestImpl;
import org.softuni.p01_extract_cookies.CookiesExtractor;
import org.softuni.p02_create_classes.CookiesReadableRequest;
import org.softuni.p02_create_classes.HttpRequestImplExtended;
import org.softuni.util.ConsoleReader;

public class Main {

    public static void main(String[] args) {
//        testCookieExtractor();
        testCreatedClasses();
    }

    private static String readRequestContentFromConsole(){
        return ConsoleReader.readAllLines();
    }

    private static void testCookieExtractor(){

        String requestContent = readRequestContentFromConsole();

        HttpRequest httpRequest = new HttpRequestImpl(requestContent);
        CookiesExtractor extractor = new CookiesExtractor(httpRequest);

        extractor.getCookies().forEach((k, v) ->
                System.out.println(String.format("%s <-> %s", k, v)));
    }

    private static void testCreatedClasses(){

        String requestContent = readRequestContentFromConsole();
        CookiesReadableRequest httpRequest = new HttpRequestImplExtended(requestContent);

        httpRequest.getCookies()
                .values()
                .forEach(System.out::println);
    }
}
