package email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

import model.Artist;
import model.Journey;
import model.Studio;
import model.User;
import org.apache.commons.io.FileUtils;
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

    String email =
      "Client tattoo image for {{ARTIST NAME}}!\n"
        + "Hi, {{ARTIST_NAME}}!\n"
        + "{{CLIENT NAME}} loved their tattoo so much they have included a " +
        "photo!\n\n"
        + "If you think this tattoo doesn't look right, contact {{CLIENT " +
        "NAME}}"
        + " at {{CLIENT EMAIL}} to organise a touch up!\n\n"
        + "Sent from inkstep. on behalf of {{CLIENT EMAIL}}\n\n";

    boolean html = false;
    try {
      email = new String(Files.readAllBytes(Paths.get("email" +
        "/ClientPhotoTemplate.html")));
      html = true;
    } catch (IOException e) {
      e.printStackTrace();
    }

    email = email.replace("{{ARTIST NAME}}", artist.name);
    email = email.replace("{{CLIENT NAME}}", user.name);
    email = email.replace("{{CLIENT EMAIL}}", user.email);
    String thumbBase = "http://inkstep.hails.info/journey/" + journey.journeyID + "/thumb/";
    email = email.replace("{{TATTOO IMGURL}}", thumbBase + "5");
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
        html
      );
    } catch (MessagingException e) {
      e.printStackTrace();
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

    String email =
      "Client request for {{ARTIST NAME}} from {{STUDIO NAME}}\n"
        + "Hi, {{ARTIST NAME}}!\n"
        + "You have received a new client request from {{CLIENT NAME}}!\n\n"
        + "{{CLIENT NAME}} would love to get a {{CLIENT CONCEPT}} on their "
        + "{{CLIENT LOCATION}} about {{CLIENT SIZE}} large.\n"
        + "{{CLIENT NAME}} is available on {{CLIENT AVAILABILITY}}"
        + " and is willing to leave a deposit\n\n"
        + "If you would like to get in touch with {{CLIENT NAME}} "
        + "their email is {{CLIENT EMAIL}}\n\n"
        + "If you want to accept this request, please reply to this email with the "
        + "range of the quote in £ (e.g. for a quote range of £100 - £150 send 100-150)\n"
        + "Please put after this an appointment time in the format: YY-MM-DD HR:MN\n"
        + "(e.g. 19-07-03 14:30 for an appointment at 2:30pm on the 3rd of July 2019)\n\n"
        + "Sent from inkstep. on behalf of {{CLIENT NAME}}\n\n";
    boolean html = false;
    try {
      email = new String(Files.readAllBytes(Paths.get("email/ClientRequestTemplate.html")));
      html = true;
    } catch (IOException e) {
      e.printStackTrace();
    }

    email = email.replace("{{ARTIST NAME}}", artist.name);
    email = email.replace("{{CLIENT NAME}}", user.name);
    email = email.replace("{{CLIENT CONCEPT}}", journey.tattooDesc);
    email = email.replace("{{CLIENT LOCATION}}", journey.position);
    email = email.replace("{{CLIENT SIZE}}", journey.size);
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

    System.out.println(email);

    JavaEmail javaEmail = new JavaEmail();

    try {
      javaEmail.sendEmail(artist.email, email, "Client Request - " + journey.journeyID,
        "inksteptattoo@gmail.com", images, html);
    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
