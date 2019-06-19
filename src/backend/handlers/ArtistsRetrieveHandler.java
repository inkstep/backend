package handlers;

import java.util.List;
import java.util.Map;

import model.Artist;
import store.InkstepStore;

public class ArtistsRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public ArtistsRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    List<Artist> artists = store.getArtists();

    return Answer.ok(dataToJson(artists));
  }
}
