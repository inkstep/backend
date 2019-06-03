package version.v1;

import email.JavaEmail;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.Route;

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
      javaEmail.sendEmail(artistEmail,toSend, "Client Request",
          "", filenames);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    return "email sent: " + toSend;
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
      String artistName = (String) requestjson.get("artist_name");
      String artistEmail = (String) requestjson.get("artist_email");
      String tattoo = (String) requestjson.get("tattoo");
      String size = (String) requestjson.get("size");
      String position = (String) requestjson.get("pos");
      String description = (String) requestjson.get("desc");

      requestjson.get("images");

      List<String> filenames = new ArrayList<>();
      //filenames.add("src/resources/image1.jpg");

      Journey.newJourney(
          userName,
          artistName,
          artistEmail,
          tattoo,
          size,
          position,
          description,
          filenames
      );

      return "{}";
    };
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
}
