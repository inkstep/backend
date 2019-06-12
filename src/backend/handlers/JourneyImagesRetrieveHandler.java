package handlers;

import java.util.List;
import java.util.Map;
import model.Journey;
import store.InkstepStore;

public class JourneyImagesRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyImagesRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  synchronized
  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    System.out.println("Attempting to retrieve images for journey_id " + urlParams.get(":id"));

    List<String> imageData = store.getImagesFromJourneyId(Integer.valueOf(urlParams.get(":id")));

    return Answer.ok(dataToJson(imageData));
  }
}
