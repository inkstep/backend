import java.util.Map;

import handlers.AbstractRequestHandler;
import handlers.Answer;
import handlers.EmptyPayload;
import store.InkstepStore;

public class HomePageRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public HomePageRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload value, Map<String, String> queryParams) {
    return Answer.ok("Home page for Inkstep API.");
  }
}
