package handlers;

import java.util.Map;

import model.Studio;
import store.InkstepStore;

public class StudioRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public StudioRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    Studio studio = store.getStudioFromID(Integer.valueOf(urlParams.get(":id")));

    return Answer.ok(dataToJson(studio));
  }
}
