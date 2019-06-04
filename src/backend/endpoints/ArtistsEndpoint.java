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

      List<Artist> artists = store.getArtists();

      JSONArray jsonArtists = new JSONArray();
      artists.forEach(a -> jsonArtists.add(new JSONObject() {
        {
          put("id", a.id);
          put("studioID", a.studioID);
          put("name", a.name);
          put("email", a.email);
        }
      }));

      return jsonArtists.toJSONString();
    };
  }
}
