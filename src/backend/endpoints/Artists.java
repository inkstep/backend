package endpoints;

import org.json.simple.JSONObject;
import spark.Route;

public class Artists {
  public static Route getArtistsRoute() {
    return (request, response) -> {
      System.out.println("Request received GET artists");

      JSONObject journeyObject = new JSONObject();

      // TODO(mm5917): Get artists from database

      journeyObject.put("artists", "artists here");

      return journeyObject.toJSONString();
    };
  }
}
