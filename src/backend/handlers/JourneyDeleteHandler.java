package handlers;

import java.util.HashMap;
import java.util.Map;

import store.InkstepStore;

public class JourneyDeleteHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyDeleteHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload request, Map<String, String> urlParams) {
    int journeyId = Integer.valueOf(urlParams.get(":id"));

    // store.delete

    //boolean fail = false;
    //if (fail) return Answer.empty(512);

    return Answer.ok(dataToJson(new HashMap<>()));
  }
}
