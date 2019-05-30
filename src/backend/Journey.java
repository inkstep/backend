import email.JavaEmail;

import java.util.List;
import javax.mail.*;

public class Journey {

  public static Object newJourney(String name, String artistName,
      String artistEmail, String tattoo, String size, String position,
      String description, List<String> filenames) {
    String emailTemplate = "Hi, %s \nYou have received a tattoo request from"
        + " %s \nTYPE %s \nSIZE: %s \nPOSITION: %s \nDESCRIPTION: %S";

    String toSend = String.format(emailTemplate, artistName, name, tattoo,
        size, position, description);

    System.out.println(toSend);

    JavaEmail javaEmail = new JavaEmail();

    try {
      javaEmail.sendEmail(artistEmail,toSend, "Client Request", filenames);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    return "email sent: " + toSend;
  }
}
