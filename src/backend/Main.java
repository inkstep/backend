import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.patch;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import handlers.ArtistRetrieveHandler;
import handlers.ArtistsRetrieveHandler;
import handlers.EmailResendHandler;
import handlers.JourneyAcceptHandler;
import handlers.JourneyCreateHandler;
import handlers.JourneyDeleteHandler;
import handlers.JourneyImagesCreateHandler;
import handlers.JourneyImagesRetrieveHandler;
import handlers.JourneyRetrieveHandler;
import handlers.JourneyTattooImageCreateHandler;
import handlers.JourneyUpdateHandler;
import handlers.JourneysRetrieveHandler;
import handlers.StudioRetrieveHandler;
import handlers.StudiosRetrieveHandler;
import handlers.ThumbnailRetrieveHandler;
import handlers.TimeRetrieveHandler;
import handlers.UserCreateHandler;
import handlers.UserEmailUpdateHandler;
import handlers.UserRetrieveHandler;
import handlers.UserUpdateHandler;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class Main {

  private static final String INKSTEP_FIREBASE_API = System.getenv("INKSTEP_FIREBASE_API");
  private static final String INKSTEP_FIREBASE_URL = System.getenv("INKSTEP_FIREBASE_URL");

  public static void main(final String[] args) throws IOException {

    // Set up Firebase
    InputStream inputStream = new ByteArrayInputStream(INKSTEP_FIREBASE_API.getBytes());
    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(inputStream))
        .setDatabaseUrl(INKSTEP_FIREBASE_URL)
        .build();
    FirebaseApp.initializeApp(options);

    // Initialise database store
    InkstepStore store = new InkstepDatabaseStore();

    // testEmail(store, 24);
    System.getenv()
      .entrySet()
      .stream()
      .filter(map -> map.getKey().startsWith("INKSTEP"))
      .forEach(map -> System.out.println(String.join("=", map.getKey(), map.getValue())));

    path("/", () -> get("", new HomePageRetrieveHandler(store)));

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

    path("/email", () -> {
      post("/resend/:id", new EmailResendHandler(store));
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
