package handlers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Artist;
import store.InkstepStore;

public class ArtistsRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public ArtistsRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override
  protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    List<Artist> artists = store.getArtists();

    final String jsonString;
    try {
      jsonString = new ObjectMapper().writeValueAsString(artists);
    } catch (IOException e) {
      e.printStackTrace();
      return Answer.empty(BAD_REQUEST);
    }

    return Answer.ok(jsonString);
  }
}
