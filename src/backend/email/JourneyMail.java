package email;

import java.io.File;
import java.util.List;
import javax.mail.MessagingException;
import java.util.ArrayList;

import model.Artist;
import model.Journey;
import model.Studio;
import model.User;
import store.InkstepStore;

public class JourneyMail {
  private InkstepStore store;
  private Journey journey;
  private List<File> images;

  public JourneyMail(InkstepStore store, Journey journey, List<File> images) {
    this.store = store;
    this.journey = journey;
    this.images = images;
  }

  public boolean sendRequestEmail() {
    Artist artist = store.getArtistFromID(journey.artistID);
    User user = store.getUserFromID(journey.userID);
    Studio studio = null;
    if (artist != null) {
      studio = store.getStudioFromID(artist.studioID);
    }
    if (artist == null || user == null || studio == null) {
      return false;
    }
    String emailTemplate =
      "Client request for " + artist.name + " from " + studio.name + "\n" + "Hi, " + artist.name
        + "!\n" + "You have received a new client request from " + user.name + "!\n\n" + user.name
        + " would love to get a " + journey.tattooDesc + " on their " + journey.position + " about "
        + journey.size + " large.\n" + user.name + " is available on " + journey.humanAvailability()
        + " and " + (journey.deposit.equals("1") ? "is" : "is not")
        + " willing to leave a deposit\n\n" + "If you would like to get in touch with " + user.name
        + " their " + "email " + "is " + user.email + ", or simply reply to this email!\n\n"
        + "Happy tattoo'ing!\n\n" + "Sent from Inkstep on behalf of " + user.name;

    System.out.println(emailTemplate);

    JavaEmail javaEmail = new JavaEmail();

    try {
      javaEmail
        .sendEmail(artist.email, emailTemplate, "Client Request - " + journey.journeyID, "inksteptattoo@gmail.com", images);
    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

}
