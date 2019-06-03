import database.PostgreDatabaseConnection;
import endpoints.Users;

import static endpoints.Journey.putJourneyRoute;
import static endpoints.Users.putUserRoute;
import static spark.Spark.put;


public class Main {

  public static void main(final String[] args) {
    System.out.println("Server is not online yay");

    Users.setDatabaseConnection(new PostgreDatabaseConnection());

    put("/user", putUserRoute());
    put("/journey", putJourneyRoute());
  }
}
