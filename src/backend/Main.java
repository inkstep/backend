import static spark.Spark.get;

import spark.Request;
import spark.Response;
import spark.Route;

public class Main {

  public static void main(String[] args) {
    get("/test", new Route() {
      public Object handle(Request request, Response response) throws Exception {
        return "User: username=test, email=test@test.net";
      }
    });
  }

}
