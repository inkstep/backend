package handlers;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.Validable;
import store.InkstepStore;

public class JourneyImagesCreateHandler extends AbstractRequestHandler<JourneyImagesCreateHandler.Payload> {

  public JourneyImagesCreateHandler(InkstepStore store) {
    super(Payload.class, store);
  }

  @Override
  protected Answer processImpl(Payload request, Map<String, String> urlParams) {
    System.out.println(request.image);

    return Answer.empty(200);
  }

  class Payload implements Validable {
    private int journeyId;
    private String image;

    @JsonCreator
    Payload(@JsonProperty("journey_id") int journeyId, @JsonProperty("image_data") String image) {
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
