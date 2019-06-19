package handlers;

import java.util.Map;

import model.Artist;
import store.InkstepStore;

public class ArtistRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public ArtistRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    Artist artists = store.getArtistFromID(Integer.valueOf(urlParams.get(":id")));

    return Answer.ok(dataToJson(artists));
  }
}
