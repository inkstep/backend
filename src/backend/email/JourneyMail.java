package email;

import javax.mail.MessagingException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import email.templates.ClientPhotoTemplate;
import email.templates.ClientRequestTemplate;
import model.Artist;
import model.Journey;
import model.Studio;
import model.User;
import store.InkstepStore;

public class JourneyMail {
  public static boolean sendPictureEmail(InkstepStore store,
                                         Journey journey, int imageId,
                                         File image) {
    Artist artist = store.getArtistFromID(journey.artistID);
    User user = store.getUserFromID(journey.userID);
    Studio studio = null;

    if (artist != null) {
      studio = store.getStudioFromID(artist.studioID);
    }

    if (artist == null || user == null || studio == null) {
      return false;
    }

    String email = new ClientPhotoTemplate().getTemplate();
    email = email.replace("{{ARTIST NAME}}", artist.name);
    email = email.replace("{{CLIENT NAME}}", user.name);
    email = email.replace("{{CLIENT EMAIL}}", user.email);
    String thumbBase = "http://inkstep.hails.info/journey/" + journey.journeyID + "/thumb/";
    email = email.replace("{{TATTOO IMGURL}}", thumbBase + imageId);
    email = email.replace("{{STUDIO NAME}}", studio.name);

    JavaEmail javaEmail = new JavaEmail();

    List<File> images = new ArrayList<>();
    images.add(image);

    try {
      javaEmail.sendEmail(
        artist.email,
        email,
        "Tattoo image!",
        user.email,
        images,
        true
      );
    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  public static boolean sendNewTattooRequestEmail(
    InkstepStore store, Journey journey, List<File> images
  ) {
    Artist artist = store.getArtistFromID(journey.artistID);
    User user = store.getUserFromID(journey.userID);
    Studio studio = null;

    if (artist != null) {
      studio = store.getStudioFromID(artist.studioID);
    }

    if (artist == null || user == null || studio == null) {
      return false;
    }

    String email = new ClientRequestTemplate().getTemplate();

    email = email.replace("{{ARTIST NAME}}", artist.name);
    email = email.replace("{{CLIENT NAME}}", user.name);
    email = email.replace("{{CLIENT CONCEPT}}", journey.tattooDesc);
    email = email.replace("{{CLIENT LOCATION}}", journey.position);
    email = email.replace("{{CLIENT SIZE}}", journey.size);
    email = email.replace("{{CLIENT STYLE}}", journey.style);
    email = email.replace("{{CLIENT AVAILABILITY}}", journey.humanAvailability());
    email = email.replace("{{CLIENT EMAIL}}", user.email);
    email = email.replace("{{INSPIRATION DESC 1}}", "1.");
    email = email.replace("{{INSPIRATION DESC 2}}", "2.");
    email = email.replace("{{INSPIRATION DESC 3}}", "3.");
    email = email.replace("{{INSPIRATION DESC 4}}", "4.");
    String thumbBase = "http://inkstep.hails.info/journey/" + journey.journeyID + "/thumb/";
    email = email.replace("{{INSPIRATION IMGURL 1}}", thumbBase + "0");
    email = email.replace("{{INSPIRATION IMGURL 2}}", thumbBase + "1");
    email = email.replace("{{INSPIRATION IMGURL 3}}", thumbBase + "2");
    email = email.replace("{{INSPIRATION IMGURL 4}}", thumbBase + "3");
    email = email.replace("{{STUDIO NAME}}", studio.name);
    email = email.replace("{{JOURNEY ID}}", String.valueOf(journey.journeyID));

    System.out.println(email);

    JavaEmail javaEmail = new JavaEmail();

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      javaEmail.sendEmail(
        artist.email, email, "New Client '" + user.name + "' #" + journey.journeyID,
        "inksteptattoo@gmail.com", images, true
      );
    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
