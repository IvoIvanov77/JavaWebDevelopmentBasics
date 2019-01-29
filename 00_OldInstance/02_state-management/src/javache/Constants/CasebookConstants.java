package javache.Constants;

public class CasebookConstants {

    public static final String EMPTY_STRING = "";
    public static final String PLACEHOLDER_REGEX = "\\$\\{(\\w*)}";
    public static final String STRING_FORMAT = "%s";

    public static final String LOGGED_IN_USER_HOME_PAGE = "/home";
    public static final String LOGGED_IN_USER_PROFILE_PAGE = "/users/profile";
    public static final String HTML_LINE_BREAK = "<br>";
    public static final String INDEX_PAGE = "/html/index.html";
    public static final String EMPTY_URL = "/";
    public static final String HTML_EXTENSION = ".html";
    public static final String LOGIN_PAGE = "/html/login.html";
    public static final String POST_LOGIN = "/login";
    public static final String POST_REGISTER = "/register";
    public static final String POST_LOGOUT = "/logout";
    public static final String CASEBOOK_SESSION_KEY = "casebook";
    public static final String COOKIE_DELETE = "deleted; expires=Thu, 01 Jan 1970 00:00:00 GMT;";
    public static final String HTML_LOGIN_HTML = "/html/login.html";
    public static final String REQUEST_BODY_KEY_EMAIL = "email";
    public static final String REQUEST_BODY_KEY_PASSWORD = "password";
    public static final String REQUEST_BODY_KEY_CONFIRM_PASSWORD = "confirm";

    public static final String SESSION_ATTRIBUTE_KEY_CURRENT_USER = "currentUser";

    private CasebookConstants(){

    }
}
