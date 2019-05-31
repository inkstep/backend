import static spark.Spark.get;
import static spark.Spark.put;

import database.PostgreDatabaseConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.Request;
import spark.Response;
import spark.Route;


public class Main {

  public static void main(final String[] args) {
    System.out.println("Server is not online yay");

    Users.setDatabaseConnection(new PostgreDatabaseConnection());

    get("/test", new Route() {
      public Object handle(Request request, Response response)
          throws Exception {
        return "User: username=test, email=test@test.net";
      }
    });

    put("/user", new Route() {
      public Object handle(Request request, Response response)
          throws Exception {
        System.out.println("Request received PUT user");
        String userName = request.queryParams("name");
        if (userName == null) {
          return "name query not supplied";
        }

        String passphrase = "JimmyHarryDannyMatty";

        JSONObject userResponse = new JSONObject();

        userResponse.put("name", userName);
        userResponse.put("passphrase", passphrase);

        return userResponse.toJSONString();
      }
    });

    get("/journey", new Route() {
      @Override
      public Object handle(Request request, Response response)
          throws Exception {
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
      }
    });

    put("/journey", new Route() {
      public Object handle(Request request, Response response)
          throws Exception {
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
      }
    });
  }
}
