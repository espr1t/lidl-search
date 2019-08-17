import static spark.Spark.*;
import spark.Request;
import spark.Response;

public class Main {
    public static void main(String[] args) {

        get("/complete", Main::handleComplete);
    }

    private static String handleComplete(Request request, Response response) {
        System.out.println(request.params());
        System.out.println(request.attributes());
        System.out.println(request.queryMap().get("string").value());
        return "Hello, World!";
    }
}
