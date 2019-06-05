package handlers;

import model.Artist;
import spark.Route;
import store.InkstepStore;

import java.util.List;
import java.util.Map;

public class ArtistRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public ArtistRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    Artist artists = store.getArtistFromID(Integer.valueOf(urlParams.get(":id")));

    return Answer.ok(dataToJson(artists));
  }
}
