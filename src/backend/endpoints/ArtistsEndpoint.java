package endpoints;

import java.util.List;

import model.Artist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.Route;
import store.ArtistsStore;

public class ArtistsEndpoint {

  private final ArtistsStore artistsStore;

  public ArtistsEndpoint(ArtistsStore artistsStore) {
    this.artistsStore = artistsStore;
  }

  public Route getArtistsRoute() {
    return (request, response) -> {
      System.out.println("Request received GET artists");

      JSONObject journeyObject = new JSONObject();

      List<Artist> artists = artistsStore.getArtists();
      String jsonArtistList = JSONArray.toJSONString(artists);

      journeyObject.put("artists", jsonArtistList);

      return journeyObject.toJSONString();
    };
  }
}
