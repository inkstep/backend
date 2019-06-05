package handlers;

import java.util.HashMap;
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

  @Override protected Answer processImpl(Journey journey, Map<String, String> urlParams) {
    int id = store.createJourney(journey);
    new JourneyMail(store, journey).sendRequestEmail();

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("journey_id", String.valueOf(id));
    }};

    return Answer.ok(dataToJson(responseMap));
  }
}
