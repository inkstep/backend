package handlers;

import java.util.List;
import java.util.Map;

import model.Studio;
import store.InkstepStore;

public class StudiosRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public StudiosRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    List<Studio> studios = store.getStudios();

    return Answer.ok(dataToJson(studios));
  }
}
