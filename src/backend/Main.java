import static spark.Spark.get;
import static spark.Spark.put;

import database.DatabaseConnection;
import database.MySQLDatabaseConnection;
import endpoints.ArtistsEndpoint;
import endpoints.JourneyEndpoint;
import endpoints.UsersEndpoint;
import store.ArtistsDatabaseStore;

public class Main {

  public static void main(final String[] args) {
    DatabaseConnection connection = new MySQLDatabaseConnection();

    ArtistsEndpoint artists = new ArtistsEndpoint(new ArtistsDatabaseStore(connection));
    get("/artists", artists.getArtistsRoute());

    JourneyEndpoint journeys = new JourneyEndpoint(connection);
    get("/journey", journeys.getJourneyRoute());
    put("/journey", journeys.putJourneyRoute());

    UsersEndpoint user = new UsersEndpoint(connection);
    put("/user", user.putUserRoute());
  }
}
