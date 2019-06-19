package handlers;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import email.JourneyMail;
import handlers.JourneyTattooImageCreateHandler.Payload;
import model.Journey;
import model.Validatable;
import org.apache.commons.io.FileUtils;
import store.InkstepStore;

public class JourneyTattooImageCreateHandler extends AbstractRequestHandler<Payload> {

  public JourneyTattooImageCreateHandler(InkstepStore store) {
    super(Payload.class, store);
  }

  @Override protected Answer processImpl(Payload payload, Map<String, String> urlParams) {
    Journey journey = store.getJourneyFromId(payload.journeyID);

    int imageId = store.putJourneyImage(journey.journeyID, payload.imageData);

    byte[] decodedBytes = Base64.getDecoder().decode(payload.imageData);
    File imageFile = new File("email_tattoo.png");
    try {
      FileUtils.writeByteArrayToFile(imageFile, decodedBytes);
      System.out.println("File created " + imageFile.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }

    JourneyMail.sendPictureEmail(store, journey, imageId, imageFile);

    return Answer.ok(dataToJson(true));
  }

  static class Payload implements Validatable {
    public final String imageData;
    public final int journeyID;

    @JsonCreator
    public Payload(
      @JsonProperty("image_data") String imageData,
      @JsonProperty("journey_id") int journeyID) {
      this.imageData = imageData;
      this.journeyID = journeyID;
    }

    // TODO(DJRHails): Add proper validation for Journey Payload
    @Override
    public boolean isValid() {
      return journeyID >= 0;
    }

    @Override
    public String toString() {
      return "Journey {" +
        ", journeyID='" + journeyID + "'" +
        "}";
    }
  }
}
