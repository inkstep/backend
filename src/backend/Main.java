import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.put;

import email.JavaEmail;
import email.JavaMessage;
import handlers.*;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import store.InkstepDatabaseStore;
import store.InkstepStore;

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

class EmailHandler implements Runnable {
  private JavaEmail javaEmail = new JavaEmail();

  @Override
  public void run() {
    while (true) {
      JavaMessage[] messages = javaEmail.receiveEmail();

      for (JavaMessage message : messages) {
        try {
          System.out.println("Message : " + message.getContent());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      /* Sleep for one minute */
      try {
        Thread.sleep(1000 * 60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
