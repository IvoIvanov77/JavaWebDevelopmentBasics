package javache;

import javache.database.entities.User;
import javache.database.services.UserService;
import javache.enums.HttpMethod;
import javache.enums.HttpStatusCode;
import javache.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javache.Constants.CasebookConstants.*;
import static javache.Constants.HTTPConstants.*;
import static javache.Constants.MessagesConstants.*;
import static javache.Constants.ServerConstants.*;

public class RequestHandler {

    private HttpRequest request;

    private HttpResponse response;

    private HttpSessionStorage sessionStorage;

    private UserService userService;

    public RequestHandler(HttpSessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    private String getMimeType(File file) {

        Path path = file.toPath();
        String mimeType = EMPTY_STRING;
        try {
            mimeType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mimeType;

    }

    private String processDynamicPageContent(String path){
        String result = EMPTY_STRING;
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            result = String.join(EMPTY_STRING, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.replaceAll(PLACEHOLDER_REGEX, STRING_FORMAT);
    }

    private byte[] processDynamicPageRequest(String path) {
        String url = this.request.getRequestUrl();
        if(url == null){
            return this.badRequest(HttpStatusCode.BAD_REQUEST.getStatusResponse().getBytes());
        }
        File file = new File(path);

        if(file.isDirectory() || !file.exists()){
            return this.badRequest(FILE_DOES_NOT_EXIST_MESSAGE.getBytes());
        }

        String content = this.processDynamicPageContent(path);
        switch (url){
            case LOGGED_IN_USER_HOME_PAGE:
                return this.processHomePage(content);
           case LOGGED_IN_USER_PROFILE_PAGE:
                return this.processProfilePage(content);
            default:
                return this.badRequest(HttpStatusCode.BAD_REQUEST.getStatusResponse().getBytes());
        }
    }

    private byte[] processProfilePage(String content) {
        User user = this.getCurrentUser();
        if(user == null){
            return this.badRequest(HttpStatusCode.BAD_REQUEST.getStatusResponse().getBytes());
        }
        return this.ok(String.format(content, user.getEmail(), user.getPassword())
                .getBytes());
    }

    private byte[] processHomePage(String content) {
        return this.ok(String.format(content, getOtherUsers())
                .getBytes());
    }

    private String getOtherUsers() {
        User user = this.getCurrentUser();
        if(user == null){
            return EMPTY_STRING;
        }
        return this.userService
                .getAll()
                .stream()
                .filter(u -> !u.getId().equals(user.getId()))
                .map(User::getEmail)
                .collect(Collectors.joining(HTML_LINE_BREAK));
    }

    private byte[] processRequest(String path, boolean isDinamic){

        if(isDinamic){
            return this.processDynamicPageRequest(path);
        }

        File file = new File(path);

        if(file.isDirectory() || !file.exists()){
            return this.badRequest(FILE_DOES_NOT_EXIST_MESSAGE.getBytes());
        }

        byte[] result = new byte[0];
        try {
            result = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.response.addHeader(CONTENT_TYPE, this.getMimeType(file));
        this.response.addHeader(CONTENT_LENGTH, EMPTY_STRING + result.length);
        this.response.addHeader(CONTENT_DISPOSITION, INLINE_CONTENT_DISPOSITION);

        return this.ok(result);
    }



    private byte[] ok(byte[] content){
        this.response.setStatusCode(HttpStatusCode.OK);
        this.response.setContent(content);
        return this.response.getBytes();
    }

    private byte[] badRequest(byte[] content) {
        this.response.setStatusCode(HttpStatusCode.BAD_REQUEST);
        this.response.setContent(content);
        return this.response.getBytes();
    }

    private byte[] notFound(byte[] content) {
        this.response.setStatusCode(HttpStatusCode.NOT_FOUND);
        this.response.setContent(content);
        return this.response.getBytes();
    }

    private byte[] redirect(byte[] content, String location) {
        this.response.setStatusCode(HttpStatusCode.SEE_OTHER);
        this.response.addHeader(LOCATION, location);
        this.response.setContent(content);
        return this.response.getBytes();
    }

    private byte[] internalServerError(byte[] content) {
        this.response.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
        this.response.setContent(content);
        return this.response.getBytes();
    }


    public byte[] handleRequest(String content){
        this.request = new HttpRequestImpl(content);
        this.response = new HttpResponseImpl();
        this.userService = new UserService();

        String url = this.request.getRequestUrl();

        if(this.request.getMethod().equals(HttpMethod.POST)){
            return this.handlePostRequest(url);
        }

        boolean isRecourse = this.request.isResource();

        if(url.equals(EMPTY_URL)){
            isRecourse = true;
            url = INDEX_PAGE;
        }

        if(!isRecourse){
            if(this.hasLoggedInUser()){
                url = url + HTML_EXTENSION;
                return this.processRequest(RESOURCES_PATH +
                        PAGES_FOLDER + url, true);
            }
            return this.redirect(HttpStatusCode.FORBIDDEN.getStatusResponse().getBytes(),
                    LOGIN_PAGE);
        }

        if(this.hasLoggedInUser() && url.endsWith(HTML_EXTENSION)){
           return this.redirect(new byte[0], LOGGED_IN_USER_PROFILE_PAGE);
        }

        return this.processRequest(RESOURCES_PATH +
               ASSETS_FOLDER +  url, false);

    }

    private byte[] handlePostRequest(String url) {
        switch (url){
            case POST_LOGIN: return this.loginUser();
            case POST_REGISTER: return this.registerUser();
            case POST_LOGOUT: return this.logOutUser();
            default:  return this.badRequest(HttpStatusCode.BAD_REQUEST.getStatusResponse().getBytes());
        }

    }

    private byte[] logOutUser() {
        HttpSession currentSession = this.getCurrentSession();
        if(currentSession != null){
            currentSession.invalidate();
            this.sessionStorage.refreshSession();
            this.response.setCookie(new HttpCookieImpl(CASEBOOK_SESSION_KEY,
                    COOKIE_DELETE));
        }
        return this.redirect(new byte[0], EMPTY_URL);
    }

    private byte[] registerUser() {
        Map<String, String> requestBody = this.request.getRequestBody();
        String email = requestBody.get(REQUEST_BODY_KEY_EMAIL);
        String password = requestBody.get(REQUEST_BODY_KEY_PASSWORD);
        String confirm = requestBody.get(REQUEST_BODY_KEY_CONFIRM_PASSWORD);


        if(email == null || password == null || confirm == null){
            return this.badRequest(INVALID_REQUEST_MESSAGE.getBytes());
        }
        if(this.userService.registerUser(email, password, confirm)){
            return this.redirect(new byte[0], HTML_LOGIN_HTML);
        }
        return this.badRequest(UNSUCCESSFUL_REGISTRATION_MESSAGE.getBytes());

    }

    private byte[] loginUser() {
        Map<String, String> requestBody = this.request.getRequestBody();
        String email = requestBody.get(REQUEST_BODY_KEY_EMAIL);
        String password = requestBody.get(REQUEST_BODY_KEY_PASSWORD);

        if(email == null || password == null){
            return this.badRequest(INVALID_REQUEST_MESSAGE.getBytes());
        }
        User user = this.userService.login(email, password);
        if(user != null){
            this.createSession(user);
            return this.redirect(new byte[0], LOGGED_IN_USER_PROFILE_PAGE);
        }
        return this.badRequest(HttpStatusCode.UNAUTHORIZED.getStatusResponse().getBytes());
    }

    private HttpSession getCurrentSession() {
        HttpCookie cookie = this.request.getCookies().get(CASEBOOK_SESSION_KEY);
        return cookie != null ?
                this.sessionStorage.getById(cookie.getValue()) :
                null;
    }

    private boolean hasCurrentSession(){
        return this.getCurrentSession() != null;
    }

    private boolean hasLoggedInUser(){
        return this.getCurrentUser() != null;
    }

    private User getCurrentUser(){
        HttpSession currentSession = this.getCurrentSession();
        if(currentSession == null ){
            return null;
        }

        return (User) this.sessionStorage
                .getById(currentSession.getId())
                .getAttributes()
                .get(SESSION_ATTRIBUTE_KEY_CURRENT_USER);
    }

    private void createSession(User user){
        if(this.hasCurrentSession()){
            return;
        }
        HttpSession session = new HttpSessionImpl();
        session.addAttribute(SESSION_ATTRIBUTE_KEY_CURRENT_USER, user);
        this.sessionStorage.addSession(session);
        this.response.setCookie(new HttpCookieImpl(CASEBOOK_SESSION_KEY, session.getId()));
    }
}
