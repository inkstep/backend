import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.put;

import handlers.ArtistsRetrieveHandler;
import handlers.JourneyCreateHandler;
import handlers.JourneyImagesCreateHandler;
import handlers.JourneyRetrieveHandler;
import handlers.UserCreateHandler;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class Main {

  public static void main(final String[] args) {
    InkstepStore store = new InkstepDatabaseStore();

    get("/artists", new ArtistsRetrieveHandler(store));

    path("/journey", () -> {
      get("", new JourneyRetrieveHandler(store));
      put("", new JourneyCreateHandler(store));
      put("/image", new JourneyImagesCreateHandler(store));
    });

    put("/user", new UserCreateHandler(store));
  }
}
