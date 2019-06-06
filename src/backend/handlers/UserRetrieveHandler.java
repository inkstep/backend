package handlers;

import model.Artist;
import model.User;
import store.InkstepStore;

import java.util.Map;

public class UserRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public UserRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    User user = store.getUserFromID(Integer.valueOf(urlParams.get(":id")));

    return Answer.ok(dataToJson(user));
  }
}
