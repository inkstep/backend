import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.put;

import endpoints.ArtistsEndpoint;
import endpoints.JourneyEndpoint;
import endpoints.UsersEndpoint;
import handlers.JourneyCreateHandler;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class Main {

  public static void main(final String[] args) {
    InkstepStore store = new InkstepDatabaseStore();

    ArtistsEndpoint artists = new ArtistsEndpoint(store);
    get("/artists", artists.getArtistsRoute());

    JourneyEndpoint journeys = new JourneyEndpoint(store);

    path("/journey", () -> {
      get("", journeys.getJourneyRoute());
      put("", new JourneyCreateHandler(store));
      put("/image", journeys.putJourneyRouteImage());
    });

    UsersEndpoint user = new UsersEndpoint(store);
    put("/user", user.putUserRoute());
  }
}
