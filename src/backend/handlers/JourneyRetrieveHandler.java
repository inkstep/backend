package handlers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Journey;
import store.InkstepStore;

public class JourneyRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override
  protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    final List<Journey> list = new ArrayList<Journey>(2);
    list.add(new Journey(0, 0, "Star", "10cm", "Neck", "0000000", "1", 0));
    list.add(new Journey(0, 1, "Flowers", "20cm", "Chest", "0000000", "1", 0));

    String jsonOut;
    try {
      jsonOut = new ObjectMapper().writeValueAsString(list);
    } catch (IOException e) {
      e.printStackTrace();
      return Answer.empty(BAD_REQUEST);
    }

    return Answer.ok(jsonOut);
  }
}
