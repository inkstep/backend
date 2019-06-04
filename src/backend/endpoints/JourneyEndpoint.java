package endpoints;

import javax.mail.MessagingException;
import java.util.ArrayList;

import email.JavaEmail;
import model.Artist;
import model.Journey;
import model.Studio;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.Route;
import store.InkstepStore;

public class JourneyEndpoint {

  private final InkstepStore store;

  public JourneyEndpoint(InkstepStore store) {
    this.store = store;
  }

  public Route getJourneyRoute() {
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

  public Route putJourneyRoute() {
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

      Studio studio = new Studio(studioName);
      Artist artist = new Artist(artistName, artistEmail, studio);
      Journey journey = new Journey(userName, userEmail, artist, studio, tattooDesc, size,
        position, availability, deposit);

      journey.sendRequestEmail();

      return "{}";
    };
  }
}