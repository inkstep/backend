package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import email.JavaEmail;
import email.JourneyMail;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Validatable;
import store.InkstepStore;

public class JourneyImagesCreateHandler
  extends AbstractRequestHandler<JourneyImagesCreateHandler.Payload> {

  public JourneyImagesCreateHandler(InkstepStore store) {
    super(Payload.class, store);
  }

  @Override
  protected Answer processImpl(Payload request, Map<String, String> urlParams) {
    System.out.println("Received img for journey " + request.getJourneyId());

    int imageId = store.putJourneyImage(request.getJourneyId(),
      request.getImage());

    boolean sendEmail = store.hasGotAllImages(request.getJourneyId());

    if (sendEmail) {
      List<File> images = store.getImagesFromJourneyId(request.getJourneyId());

      new JourneyMail(
        store,
        store.getJourneyFromId(request.getJourneyId()),
        images
      ).sendRequestEmail();
    }

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("image_id", String.valueOf(imageId));
    }};

    return Answer.ok(dataToJson(responseMap));
  }

  static class Payload implements Validatable {

    private int journeyId;
    private String image;

    @JsonCreator
    Payload(@JsonProperty("journey_id") int journeyId,
      @JsonProperty("image_data") String image) {
      this.journeyId = journeyId;
      this.image = image;
    }

    public String getImage() {
      return image;
    }

    public int getJourneyId() {
      return journeyId;
    }

    @Override
    public boolean isValid() {
      return journeyId >= 0 && image.length() > 0;
    }
  }
}
