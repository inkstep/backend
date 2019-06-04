package model;

import email.JavaEmail;
import java.util.ArrayList;
import javax.mail.MessagingException;

public class Journey {

  public final String noRefImages;
  private final User user;
  private final Artist artist;
  private final String tattooDesc;
  private final String size;
  private final String position;
  private final String availability;
  private final String deposit;

  public Journey(User user, Artist artist,
    String tattooDesc, String size, String position, String availability,
    String deposit, int noRefImages) {
    this.user = user;
    this.artist = artist;
    this.tattooDesc = tattooDesc;
    this.size = size;
    this.position = position;
    this.availability = availability;
    this.deposit = deposit;
    this.noRefImages = String.valueOf(noRefImages);
  }

  public void sendRequestEmail() {
    String emailTemplate =
      "Client request for " + artist.name + " from " + artist.studio.name
        + "\n" + "Hi, " + artist.name
        + "!\n" + "You have received a new client request from " + user.name
        + "!\n\n" + user.name
        + " would love to get a " + tattooDesc + " on their " + position
        + " about " + size
        + " large.\n" + user.name + " is available on "
        + translateAvailability(availability) + " and " + (
        deposit.equals("1") ? "is" : "is not")
        + " willing to leave a deposit\n\n"
        + "If you would like to get in touch with " + user.name + " their "
        + "email " + "is " + user.email
        + ", or simply reply to this email!\n\n" + "Happy tattoo'ing!\n\n"
        + "Sent from Inkstep on behalf of " + user.name;

    System.out.println(emailTemplate);

    JavaEmail javaEmail = new JavaEmail();

    try {
      javaEmail.sendEmail(artist.email, emailTemplate, "Client Request", user.email,
        new ArrayList<>());
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  private String translateAvailability(String availability) {
    String[] days = {"Mondays", "Tuesdays", "Wednesdays", "Thursdays",
      "Fridays", "Saturdays", "Sundays"};
    String readableAvailability = "";
    for (int i = 0; i < 7; i++) {
      if (availability.charAt(i) == '1') {
        if (readableAvailability != "") {
          readableAvailability += ", ";
        }
        readableAvailability += days[i];
      }
    }

    return readableAvailability;
  }
}
