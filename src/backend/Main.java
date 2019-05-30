import static spark.Spark.get;
import static spark.Spark.put;

import database.PostgreDatabaseConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.MultipartConfigElement;

import org.json.simple.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class Main {

  public static void main(final String[] args) {
    Users.setDatabaseConnection(new PostgreDatabaseConnection());

    get("/test", new Route() {
      public Object handle(Request request, Response response) throws Exception {
        return "User: username=test, email=test@test.net";
      }
    });

    put("/user", new Route() {
      public Object handle(Request request, Response response) throws Exception {
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
      public Object handle(Request request, Response response) throws Exception {
        JSONObject journeyObject = new JSONObject();

        journeyObject.put("user_name" , "Jimmy");
        journeyObject.put("artist_name" , "Rickay");
        journeyObject.put("artist_email" , "Rickay@theemail.com");
        journeyObject.put("tattoo" , "star");
        journeyObject.put("size" , "10cm");
        journeyObject.put("pos" , "neck");
        journeyObject.put("desc" , "I like tattoo's");
        
        return journeyObject.toJSONString();
      }
    });

    put("/journey", new Route() {
      public Object handle(Request request, Response response) throws Exception {
        /*
        try (InputStream is = request.raw().getPart("image1").getInputStream()) {
          byte[] buffer = new byte[is.available()];
          is.read(buffer);

          File targetFile = new File("src/resources/image1.jpg");
          OutputStream outStream = new FileOutputStream(targetFile);
          outStream.write(buffer);
        }

        try (InputStream is =
               request.raw().getPart("image2").getInputStream()) {
          byte[] buffer = new byte[is.available()];
          System.out.println(buffer.length);
          is.read(buffer);

          File targetFile = new File("src/resources/image2.jpg");
          OutputStream outStream = new FileOutputStream(targetFile);
          outStream.write(buffer);
        }
        */

        String userName = request.queryParams("user_name");
        if (userName == null) {
          return "userName query not supplied";
        }

        String passphrase = request.queryParams("passphrase");
        if (passphrase == null) {
          return "passphrase query not supplied";
        }

        String artistName = request.queryParams("artist_name");
        if (artistName == null) {
          return "artistName query not supplied";
        }

        String artistEmail = request.queryParams("artist_email");
        if (artistEmail == null) {
          return "artistEmail query not supplied";
        }

        String tattoo = request.queryParams("tattoo");
        if (tattoo == null) {
          return "tattoo query not supplied";
        }

        String size = request.queryParams("size");
        if (size == null) {
          return "size query not supplied";
        }

        String position = request.queryParams("pos");
        if (position == null) {
          return "pos query not supplied";
        }

        String description = request.queryParams("desc");
        if (description == null) {
          return "desc query not supplied";
        }

        List<String> filenames = new ArrayList<>();

        filenames.add("src/resources/image1.jpg");

        return Journey.newJourney(
            userName,
            artistName,
            artistEmail,
            tattoo,
            size,
            position,
            description,
            filenames
        );
      }
    });
  }
}
