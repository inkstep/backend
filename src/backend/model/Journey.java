package model;

import javax.mail.MessagingException;
import java.util.ArrayList;

import email.JavaEmail;

public class Journey implements Validable {

  public final int userID;
  public final int artistID;

  public final String tattooDesc;
  public final String size;
  public final String position;
  public final String availability;
  public final String deposit;
  public final String noRefImages;

  public Journey(int userID, int artistID, String tattooDesc,
    String size, String position, String availability, String deposit, int noRefImages) {
    this.userID = userID;
    this.artistID = artistID;
    this.tattooDesc = tattooDesc;
    this.size = size;
    this.position = position;
    this.availability = availability;
    this.deposit = deposit;
    this.noRefImages = String.valueOf(noRefImages);
  }

//  public void sendRequestEmail() {
//    String emailTemplate =
//      "Client request for " + artist.name + " from " + studio.name + "\n" + "Hi, " + artist.name
//        + "!\n" + "You have received a new client request from " + userName + "!\n\n" + userName
//        + " would love to get a " + tattooDesc + " on their " + position + " about " + size
//        + " large.\n" + userName + " is available on " + translateAvailability(availability)
//              + " and " + (deposit
//        .equals("1") ? "is" : "is not") + " willing to leave a deposit\n\n"
//        + "If you would like to get in touch with " + userName + " their email is " + userEmail
//        + ", or simply reply to this email!\n\n" + "Happy tattoo'ing!\n\n"
//        + "Sent from Inkstep on behalf of " + userName;
//
//    String toSend = String
//      .format(emailTemplate, artist.name, studio.name, artist.name, userName, userName, tattooDesc,
//        position, size, userName, availability, deposit.equals("Yes") ? "is" : "is not", userName,
//        userEmail, userName);
//
//    System.out.println(toSend);
//
//    JavaEmail javaEmail = new JavaEmail();
//
//    try {
//      javaEmail.sendEmail(artist.email, toSend, "Client Request", userEmail, new ArrayList<>());
//    } catch (MessagingException e) {
//      e.printStackTrace();
//    }
//  }

  private String translateAvailability(String availability) {
    String[] days = {"Mondays", "Tuesdays", "Wednesdays", "Thursdays",
                     "Fridays", "Saturdays", "Sundays"};
    StringBuilder readableAvailability = new StringBuilder();
    for (int i = 0; i < 7; i++) {
      if (availability.charAt(i) == '1') {
        if (!readableAvailability.toString().equals("")) {
          readableAvailability.append(", ");
        }
        readableAvailability.append(days[i]);
      }
    }

    return readableAvailability.toString();
  }

  // TODO(DJRHails): Add proper validation for Journey Payload
  @Override
  public boolean isValid() {
    return true;
  }
}
