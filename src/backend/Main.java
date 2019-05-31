import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.put;
import static version.v1.Journey.getJourneyRoute;
import static version.v1.Journey.putJourneyRoute;
import static version.v1.Users.putUserRoute;

import database.PostgreDatabaseConnection;
import version.v1.Users;


public class Main {

  public static void main(final String[] args) {
    System.out.println("Server is not online yay");

    Users.setDatabaseConnection(new PostgreDatabaseConnection());

    path("/version/v1", () -> {
      put("/user", putUserRoute());

      get("/journey", getJourneyRoute());
      put("/journey", putJourneyRoute());
    });
  }

}
