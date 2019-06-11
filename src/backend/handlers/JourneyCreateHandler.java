package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import handlers.JourneyCreateHandler.Payload;
import java.util.HashMap;
import java.util.Map;

import model.Journey;
import model.JourneyStage;
import model.Validatable;
import store.InkstepStore;

public class JourneyCreateHandler extends AbstractRequestHandler<Payload> {

  private InkstepStore store;

  public JourneyCreateHandler(InkstepStore store) {
    super(Payload.class, store);
    this.store = store;
  }

  @Override protected Answer processImpl(Payload payload, Map<String, String> urlParams) {
    Journey journey = new Journey(
      -1,
      payload.userID,
      payload.artistID,
      payload.tattooDesc,
      payload.size,
      payload.position,
      payload.availability,
      payload.deposit,
      payload.noRefImages,
      -1,
      -1,
      JourneyStage.WaitingQuote.toCode()
    );

    int id = store.createJourney(journey);

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("journey_id", String.valueOf(id));
    }};

    return Answer.ok(dataToJson(responseMap));
  }

  static class Payload implements Validatable {
    public final int userID;
    public final int artistID;

    public final int noRefImages;
    public final String tattooDesc;
    public final String size;
    public final String position;
    public final String availability;
    public final String deposit;

    @JsonCreator
    public Payload(
      @JsonProperty("user_id") int userID,
      @JsonProperty("artist_id") int artistID,
      @JsonProperty("tattoo_desc") String tattooDesc,
      @JsonProperty("size") String size,
      @JsonProperty("position") String position,
      @JsonProperty("availability") String availability,
      @JsonProperty("deposit") String deposit,
      @JsonProperty("ref_images") int noRefImages) {
      this.userID = userID;
      this.artistID = artistID;
      this.tattooDesc = tattooDesc;
      this.size = size;
      this.position = position;
      this.availability = availability;
      this.deposit = deposit;
      this.noRefImages = noRefImages;
    }

    public String humanAvailability() {
      String[] days =
        {"Mondays", "Tuesdays", "Wednesdays", "Thursdays", "Fridays", "Saturdays", "Sundays"};
      StringBuilder readableAvailability = new StringBuilder();
      for (int i = 0; i < 7; i++) {
        if (availability.charAt(i) == '1') {
          if (!readableAvailability.toString().equals("")) {
            readableAvailability.append(", ");
          }
          readableAvailability.append(days[i]);
        }
      }

      return readableAvailability.toString();
    }

    // TODO(DJRHails): Add proper validation for Journey Payload
    @Override
    public boolean isValid() {
      return userID >= 0 && artistID >= 0 && availability.length() == 7;
    }

    @Override
    public String toString() {
      return "Journey {" +
        " userID='" + userID + "'" +
        ", artistID='" + artistID + "'" +
        ", noRefImages='" + noRefImages + "'" +
        ", tattooDesc='" + tattooDesc + "'" +
        ", size='" + size + "'" +
        ", position='" + position + "'" +
        ", availability='" + availability + "'" +
        ", deposit='" + deposit + "'" +
        "}";
    }
  }
}
