import static endpoints.Journey.getJourneyRoute;
import static endpoints.Journey.putJourneyRoute;
import static endpoints.Users.putUserRoute;
import static spark.Spark.get;
import static spark.Spark.put;

import database.PostgreDatabaseConnection;
import endpoints.Users;

public class Main {

  public static void main(final String[] args) {
    System.out.println("Server is not online yay");

    Users.setDatabaseConnection(new PostgreDatabaseConnection());

    put("/user", putUserRoute());
    get("/journey", getJourneyRoute());
    put("/journey", putJourneyRoute());
  }
}
