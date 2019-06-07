package handlers;

import model.Journey;
import store.InkstepStore;

import java.util.List;
import java.util.Map;

public class JourneysRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneysRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    System.out.println("Retrieving journeys");

    List<Journey> journeys = store.getJourneysForUserID(Integer.valueOf(urlParams.get("user")));
    return Answer.ok(dataToJson(journeys));
  }
}
