import email.JavaEmail;
import email.JavaMessage;
import handlers.*;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import store.InkstepDatabaseStore;
import store.InkstepStore;

import static spark.Spark.*;

public class Main {

  public static void main(final String[] args) {
    InkstepStore store = new InkstepDatabaseStore();

    path("/artist", () -> {
      get("", new ArtistsRetrieveHandler(store));
      get("/:id", new ArtistRetrieveHandler(store));
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

    Thread emailThread = new Thread(new EmailHandler());
    emailThread.run();
  }
}

