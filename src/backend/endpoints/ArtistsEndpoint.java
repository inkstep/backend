package endpoints;

import database.DatabaseConnection;
import org.json.simple.JSONObject;
import spark.Route;

public class ArtistsEndpoint {

  private final DatabaseConnection connection;

  public ArtistsEndpoint(DatabaseConnection connection) {
    this.connection = connection;
  }

  public Route getArtistsRoute() {
    return (request, response) -> {
      System.out.println("Request received GET artists");

      JSONObject journeyObject = new JSONObject();

      // TODO(mm5917): Get artists from database

      journeyObject.put("artists", "artists here");

      return journeyObject.toJSONString();
    };
  }
}
