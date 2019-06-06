package handlers;

import model.Journey;
import store.InkstepStore;

import java.util.Map;

public class JourneyRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    Journey journey = store.getJourneyFromId(Integer.valueOf(urlParams.get(":id")));
    return Answer.ok(dataToJson(journey));
  }
}
