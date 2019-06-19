import static spark.Spark.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import handlers.*;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class Main {

  private static final String INKSTEP_FIREBASE_API = System.getenv("INKSTEP_FIREBASE_API");

  public static void main(final String[] args) throws IOException {

    InputStream inputStream = new ByteArrayInputStream(INKSTEP_FIREBASE_API.getBytes());
    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(inputStream))
        .setDatabaseUrl("https://inkstep-d738d.firebaseio.com")
        .build();

    FirebaseApp.initializeApp(options);

    InkstepStore store = new InkstepDatabaseStore();

    port(6789);

    path("/artist", () -> {
      get("", new ArtistsRetrieveHandler(store));
      get("/:id", new ArtistRetrieveHandler(store));
    });

    path("/journey", () -> {
      put("", new JourneyCreateHandler(store));
      get("", new JourneysRetrieveHandler(store));
      delete("/:id", new JourneyDeleteHandler(store));
      get("/:id", new JourneyRetrieveHandler(store));
      put("/image", new JourneyImagesCreateHandler(store));
      put("/image/tattoo", new JourneyTattooImageCreateHandler(store));
      get("/:id/images", new JourneyImagesRetrieveHandler(store));
      get("/:jid/thumb/:iid", new ThumbnailRetrieveHandler(store));
      patch("/:id", new JourneyUpdateHandler(store));
      post("/:id", new JourneyAcceptHandler(store));
    });

    path("/user", () -> {
      put("", new UserCreateHandler(store));
      get("/:id", new UserRetrieveHandler(store));
      put("/:id/email", new UserEmailUpdateHandler(store));
      patch("/:id", new UserUpdateHandler(store));
    });

    path("/studio", () -> {
      get("", new StudiosRetrieveHandler(store));
      get("/:id", new StudioRetrieveHandler(store));
    });

    path("/time", () -> {
      get("", new TimeRetrieveHandler(store));
    });
  }
}
