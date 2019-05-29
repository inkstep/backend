import static spark.Spark.get;
import static spark.Spark.put;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
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

    put("/user", new Route() {
      public Object handle(Request request, Response response) throws Exception {
        String userName = request.queryParams("name");
        if (userName == null) {
          return "name param not supplied";
        }

        return Users.putUser(userName);
      }
    });
  }
}
