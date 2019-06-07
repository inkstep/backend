package handlers;

import java.util.Map;
import model.User;
import store.InkstepStore;

public class UserLogonHandler extends AbstractRequestHandler<EmptyPayload> {

  public UserLogonHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload empty, Map<String, String> urlParams) {
    User user = store.getUserFromPassphraseEmail(urlParams.get(":passphrase"), urlParams.get(
      ":email"));

    return Answer.ok(dataToJson(user));
  }
}
