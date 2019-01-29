import org.softuni.broccolina.solet.BaseHttpSolet;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.broccolina.solet.HttpSoletResponse;
import org.softuni.broccolina.solet.WebSolet;
import org.softuni.javache.http.HttpStatus;


@WebSolet(route = "/")
public class HomeSolet extends BaseHttpSolet {

    @Override
    protected void doGet(HttpSoletRequest request, HttpSoletResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        response.setContent((
                        "<head>" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">" +
                        "</head>" +
                        "<body>" +
                            "<h1>Hello from demo solet app</h1>" +
                                "<button type=\"button\" class=\"btn btn-primary\">Click me</button>" +
                        "</body>").getBytes());

//        try {
//            response.getOutputStream().write(response.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
