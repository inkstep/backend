package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import email.JourneyMail;
import handlers.ArtistImageCreateHandler.Payload;
import java.util.Map;
import model.Artist;
import model.User;
import model.Validatable;
import store.InkstepStore;

public class ArtistImageCreateHandler extends AbstractRequestHandler<Payload> {

  public ArtistImageCreateHandler(InkstepStore store) {
    super(Payload.class, store);
  }

  @Override protected Answer processImpl(Payload payload, Map<String, String> urlParams) {
    Artist artist = store.getArtistFromID(payload.artistID);
    User user = store.getUserFromID(payload.userID);

    JourneyMail.sendPictureEmail(payload.imageData, artist, user);

    return Answer.ok(dataToJson(true));
  }

  static class Payload implements Validatable {
    public final String imageData;
    public final int artistID;
    public final int userID;

    @JsonCreator
    public Payload(
      @JsonProperty("image_data") String imageData,
      @JsonProperty("artist_id") int artistID,
      @JsonProperty("user_id") int userID) {
      this.imageData = imageData;
      this.artistID = artistID;
      this.userID = userID;
    }

    // TODO(DJRHails): Add proper validation for Journey Payload
    @Override
    public boolean isValid() {
      return artistID > 0;
    }

    @Override
    public String toString() {
      return "Journey {" +
        ", artistID='" + artistID + "'" +
        "}";
    }
  }
}
