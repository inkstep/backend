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

        return Users.putUser(userName);
      }
    });

    put("/journey", new Route() {
      public Object handle(Request request, Response response) throws Exception {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
        request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);

        String userName = request.queryParams("userName");
        if (userName == null) {
          return "userName query not supplied";
        }

        String artistName = request.queryParams("artistName");
        if (artistName == null) {
          return "artistName query not supplied";
        }

        String artistEmail = request.queryParams("artistEmail");
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

        try (InputStream is =
            request.raw().getPart("image1").getInputStream()) {
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
