package handlers;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import email.JourneyMail;
import model.Journey;
import store.InkstepStore;

public class JourneyCreateHandler extends AbstractRequestHandler<Journey> {

  private InkstepStore store;

  public JourneyCreateHandler(InkstepStore store) {
    super(Journey.class, store);
    this.store = store;
  }

  @Override
  protected Answer processImpl(Journey journey, Map<String, String> urlParams) {
    int id = store.createJourney(journey);

    new JourneyMail(store, journey).sendRequestEmail();

    String jsonResponse;
    try {
      Map<String, String> responseMap = Map.of("journey_id", String.valueOf(id));
      jsonResponse = new ObjectMapper().writeValueAsString(responseMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return Answer.empty(BAD_REQUEST);
    }

    return Answer.ok(jsonResponse);
  }
}
