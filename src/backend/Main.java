import static spark.Spark.*;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessagingException;
import handlers.*;
import store.InkstepDatabaseStore;
import store.InkstepStore;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

  private static final String INKSTEP_FIREBASE_API = System.getenv("INKSTEP_FIREBASE_API");

  public static void main(final String[] args) throws IOException, FirebaseMessagingException {

    InputStream inputStream = new ByteArrayInputStream(INKSTEP_FIREBASE_API.getBytes());
    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(inputStream))
        .setDatabaseUrl("https://inkstep-d738d.firebaseio.com")
        .build();

    FirebaseApp.initializeApp(options);

    InkstepStore store = new InkstepDatabaseStore();

    path("/artist", () -> {
      get("", new ArtistsRetrieveHandler(store));
      get("/:id", new ArtistRetrieveHandler(store));
      put("/image", new ArtistImageCreateHandler(store));
    });

    path("/journey", () -> {
      put("", new JourneyCreateHandler(store));
      get("", new JourneysRetrieveHandler(store));
      get("/:id", new JourneyRetrieveHandler(store));
      put("/image", new JourneyImagesCreateHandler(store));
      get("/:id/images", new JourneyImagesRetrieveHandler(store));
      patch("/:id", new JourneyUpdateHandler(store));
    });

    path("/user", () -> {
      put("", new UserCreateHandler(store));
      get("/:id", new UserRetrieveHandler(store));
      get("/:passphrase/:email", new UserLogonHandler(store));
    });

    path("/studio", () -> {
      get("", new StudiosRetrieveHandler(store));
      get("/:id", new StudioRetrieveHandler(store));
    });

    path("/time", () -> {
      get("", new TimeRetrieveHandler(store));
    });

    Thread emailThread = new Thread(new EmailHandler());
    emailThread.run();
  }
}

