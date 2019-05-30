import email.JavaEmail;

import javax.mail.*;

public class Journey {

  public static Object newJourney(String name, String artistName,
      String artistEmail, String tattoo, String size, String position,
      String description) {
    String emailTemplate = "Hi, %s \nYou have received a tattoo request from"
        + " %s \nTYPE %s \nSIZE: %s \nPOSITION: %s \nDESCRIPTION: %S";

    String toSend = String.format(emailTemplate, artistName, name, tattoo,
        size, position, description);

    System.out.println(toSend);

    JavaEmail javaEmail = new JavaEmail();

    try {
      javaEmail.sendEmail(artistEmail,toSend, "Client Request");
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    return "email sent: " + toSend;
  }
}
