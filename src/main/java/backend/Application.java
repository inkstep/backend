package backend;

import static spark.Spark.get;

public class Application {

  public static void main(String[] args) {
    get("/test", (request, response) -> "User: username=test, email=test@test.net");
  }

}
