package endpoints;

import email.JavaEmail;
import java.util.ArrayList;
import javax.mail.MessagingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.Route;

public class Journey {

  private static void newJourney(String userName, String userEmail, String artistName,
    String artistEmail, String studioName, String tattooDesc, String size, String position,
    String availability, String deposit) {
    String emailTemplate =
        "Client request for %s from %s\n"
            + "Hi, %s!\n"
            + "You have received a new client request from %s!\n\n"
            + "%s would love to get a %s on their %s about %s large.\n"
            + "%s is available on %s and %s willing to leave a deposit\n\n"
            + "If you would like to get in touch with %s their email is %s, "
            + "or simply reply to this email!\n\n"
            + "Happy tattoo'ing!\n\n"
            + "Sent from Inkstep on behalf of %s";

    String toSend = String.format(emailTemplate, artistName, studioName,
        userName, userName, tattooDesc, position, size, userName, availability,
        deposit.equals("Yes") ? "is" : "is not", userName, userEmail, userName);

    System.out.println(toSend);

    JavaEmail javaEmail = new JavaEmail();

    try {
      javaEmail.sendEmail(artistEmail, toSend, "Client Request",
          userEmail, new ArrayList<>());
    } catch (MessagingException e) {
      e.printStackTrace();
    }

  }

  public static Route getJourneyRoute() {
    return (request, response) -> {
      System.out.println("Request received GET journey");

      JSONObject journeyObject = new JSONObject();

      journeyObject.put("user_name", "Jimmy");
      journeyObject.put("artist_name", "Rickay");
      journeyObject.put("artist_email", "Rickay@theemail.com");
      journeyObject.put("tattoo", "star");
      journeyObject.put("size", "10cm");
      journeyObject.put("pos", "neck");
      journeyObject.put("desc", "I like tattoo's");

      return journeyObject.toJSONString();
    };
  }

  public static Route putJourneyRoute() {
    return (request, response) -> {
      System.out.println("Request received PUT journey");

      String body = request.body();

      System.out.println(body);

      JSONParser parser = new JSONParser();
      JSONObject requestjson = (JSONObject) parser.parse(body);

      System.out.println(requestjson.toJSONString());

      String userName = (String) requestjson.get("user_name");
      String userEmail = (String) requestjson.get("user_email");
      String artistName = (String) requestjson.get("artist_name");
      String artistEmail = (String) requestjson.get("artist_email");
      String studioName = (String) requestjson.get("studio_name");
      String tattooDesc = (String) requestjson.get("tattoo_desc");
      String size = (String) requestjson.get("size");
      String position = (String) requestjson.get("position");
      String availability = (String) requestjson.get("availability");
      String deposit = (String) requestjson.get("deposit");

      Journey.newJourney(userName, userEmail, artistName, artistEmail,
        studioName, tattooDesc, size, position, availability, deposit);

      return "{}";
    };
  }
}
