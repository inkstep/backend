package endpoints;

import java.util.List;

import model.Artist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.Route;
import store.InkstepStore;

public class ArtistsEndpoint {

  private final InkstepStore store;

  public ArtistsEndpoint(InkstepStore store) {
    this.store = store;
  }

  public Route getArtistsRoute() {
    return (request, response) -> {
      System.out.println("Request received GET artists");

      JSONObject journeyObject = new JSONObject();

      List<Artist> artists = store.getArtists();
      String jsonArtistList = JSONArray.toJSONString(artists);

      journeyObject.put("artists", jsonArtistList);

      return journeyObject.toJSONString();
    };
  }
}
