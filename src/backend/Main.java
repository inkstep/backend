import static spark.Spark.get;
import static spark.Spark.put;

import store.InkstepDatabaseStore;
import endpoints.ArtistsEndpoint;
import endpoints.JourneyEndpoint;
import endpoints.UsersEndpoint;
import store.InkstepStore;

public class Main {

  public static void main(final String[] args) {
    InkstepStore store = new InkstepDatabaseStore();

    ArtistsEndpoint artists = new ArtistsEndpoint(store);
    get("/artists", artists.getArtistsRoute());

    JourneyEndpoint journeys = new JourneyEndpoint(store);
    get("/journey", journeys.getJourneyRoute());
    put("/journey", journeys.putJourneyRoute());

    UsersEndpoint user = new UsersEndpoint(store);
    put("/user", user.putUserRoute());
  }
}
