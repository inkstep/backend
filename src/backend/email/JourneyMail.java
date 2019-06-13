package email;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

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

  public boolean sendNewTattooRequestEmail() {
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
        "Client request for " + artist.name + " from " + studio.name + "\n"
            + "Hi, " + artist.name
            + "!\n" + "You have received a new client request from " + user.name
            + "!\n\n" + user.name
            + " would love to get a " + journey.tattooDesc + " on their "
            + journey.position + " about "
            + journey.size + " large.\n" + user.name + " is available on "
            + journey.humanAvailability()
            + " and is willing to leave a deposit\n\n"
            + "If you would like to get in touch with " + user.name
            + " their " + "email " + "is " + user.email + "\n\n"
            + "If you want to accept this request, please reply to this email with the "
            + "range of the quote in £ (e.g. for a quote range of £100 - £150 send 100-150)\n"
            + "Please put after this an appointment time in the format: YY-MM-DD HR:MN\n"
            + "(e.g. 19-07-03 14:30 for an appointment at 2:30pm on the 3rd of July 2019)\n\n"
            + "Sent from inkstep. on behalf of " + user.name + "\n\n";

    System.out.println(emailTemplate);

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

//  public boolean sendQuoteAcceptedEmail() {
//    Artist artist = store.getArtistFromID(journey.artistID);
//    User user = store.getUserFromID(journey.userID);
//    Studio studio = null;
//    if (artist != null) {
//      studio = store.getStudioFromID(artist.studioID);
//    }
//    if (artist == null || user == null || studio == null) {
//      return false;
//    }
//
//    String emailContent =
//      "Hey " + artist.name + ",\n\n" + user.name + " has agreed to your quote of "
//              + journey.quoteLower + "-" + journey.quoteUpper + ".\n\n"
//              + "As a reminder, they are available on " + journey.humanAvailability() + "\n"
//              + "To get them booked in, please reply to this email with an appointment time in the "
//              + "format: YY-MM-DD HR:MN"
//              + "\n(e.g. 19-07-03 14:30 for an appointment at 2:30pm on the 3rd of July 2019)\n\n"
//              + "Sent from inkstep. on behalf of " + user.name + "\n\n";
//
//    System.out.println(emailContent);
//
//    JavaEmail javaEmail = new JavaEmail();
//
//    try {
//      javaEmail.sendEmail(artist.email, emailContent, "Quote Accepted - " + journey.journeyID,
//        "inksteptattoo@gmail.com", images);
//    } catch (MessagingException e) {
//      e.printStackTrace();
//      return false;
//    }
//    return true;
//  }
}
