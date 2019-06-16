package handlers;

import java.util.Map;

import model.User;
import store.InkstepStore;

public class UserRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public UserRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    User user = store.getUserFromID(Integer.valueOf(urlParams.get(":id")));
    if (user == null) {
      return Answer.code(404);
    }
    return Answer.ok(dataToJson(user));
  }
}
