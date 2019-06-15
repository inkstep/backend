package email;

import java.util.ArrayList;
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
  public static boolean sendPictureEmail(String imageData, Artist artist, User user) {
    byte[] decodedBytes = Base64.getDecoder().decode(imageData);
    File imageFile = new File("tattoo_image.png");
    try {
      FileUtils.writeByteArrayToFile(imageFile, decodedBytes);
      String emailTemplate =
        "Client tattoo image for " + artist.name + "\n"
          + "Hi, " + artist.name + "!\n"
          + user.id + " loved their tattoo so much they have included a photo!\n\n"
          + "Sent from inkstep. on behalf of " + user.name + "\n\n";

      JavaEmail javaEmail = new JavaEmail();

      List<File> imageList = new ArrayList<>();
      imageList.add(imageFile);

      javaEmail.sendEmail(artist.email, emailTemplate, "Tattoo image!", user.email, imageList);
    } catch (Exception e) {
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
      javaEmail.sendEmail(artist.email, emailTemplate, "Client Request - " + journey.journeyID,
        "inksteptattoo@gmail.com", images);
    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
