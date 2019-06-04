package model;

import javax.mail.MessagingException;
import java.util.ArrayList;

import email.JavaEmail;

public class Journey {

  private final String userName;
  private final String userEmail;

  private final Artist artist;
  private final Studio studio;

  private final String tattooDesc;
  private final String size;
  private final String position;
  private final String availability;
  private final String deposit;

  public Journey(String userName, String userEmail, Artist artist, Studio studio, String tattooDesc,
    String size, String position, String availability, String deposit) {
    this.userName = userName;
    this.userEmail = userEmail;
    this.artist = artist;
    this.studio = studio;
    this.tattooDesc = tattooDesc;
    this.size = size;
    this.position = position;
    this.availability = availability;
    this.deposit = deposit;
  }

  public void sendRequestEmail() {
    String emailTemplate =
      "Client request for " + artist.name + " from " + studio.name + "\n" + "Hi, " + artist.name
        + "!\n" + "You have received a new client request from " + userName + "!\n\n" + userName
        + " would love to get a " + tattooDesc + " on their " + position + " about " + size
        + " large.\n" + userName + " is available on " + availability + " and " + (deposit
        .equals("1") ? "is" : "is not") + " willing to leave a deposit\n\n"
        + "If you would like to get in touch with " + userName + " their email is " + userEmail
        + ", or simply reply to this email!\n\n" + "Happy tattoo'ing!\n\n"
        + "Sent from Inkstep on behalf of " + userName;

    String toSend = String
      .format(emailTemplate, artist.name, studio.name, artist.name, userName, userName, tattooDesc,
        position, size, userName, availability, deposit.equals("Yes") ? "is" : "is not", userName,
        userEmail, userName);

    System.out.println(toSend);

    JavaEmail javaEmail = new JavaEmail();

    try {
      javaEmail.sendEmail(artist.email, toSend, "Client Request", userEmail, new ArrayList<>());
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
