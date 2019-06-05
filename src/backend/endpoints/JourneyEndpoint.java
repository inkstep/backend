package endpoints;

import model.Artist;
import model.Journey;
import model.Studio;
import org.json.simple.JSONArray;
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

      JSONObject journeyObject1 = new JSONObject() {
        {
          put("user_name", "Jimmy");
          put("artist_name", "Rickay");
          put("artist_email", "Rickay@theemail.com");
          put("tattoo", "star");
          put("size", "10cm");
          put("pos", "neck");
          put("desc", "I like tattoo's");
        }
      };
      JSONArray journeys = new JSONArray() {
        {
          add(journeyObject1);
        }
      };


      return journeys.toJSONString();
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
      String noRefImages = (String) requestjson.get("ref_images");

      Studio studio = new Studio(studioName);
      Artist artist = new Artist(artistName, artistEmail, studio);

      Journey journey = new Journey(userName, userEmail, artist, studio, tattooDesc, size,
          position, availability, deposit, 0);

      int journeyId = store.putJourney(journey);

      System.out.println(journeyId);

      JSONObject responsejson = new JSONObject();
      responsejson.put("journey_id", journeyId);

      System.out.println(responsejson.toJSONString());

      return responsejson.toJSONString();
    };
  }

  public Route putJourneyRouteImage() {
    return (request, response) -> {
      System.out.println(request.body());

      JSONParser parser = new JSONParser();
      JSONObject requestjson = (JSONObject) parser.parse(request.body());

      String journeyId = (String) requestjson.get("journey_id");
      String image = (String) requestjson.get("image_data");

      System.out.println(image);

      JSONObject responsejson = new JSONObject();
      responsejson.put("image_id", journeyId);

      return responsejson.toJSONString();
    };
  }
}
