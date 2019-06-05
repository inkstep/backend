package handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.User;
import model.Validatable;
import model.Passphrase;
import store.InkstepStore;

public class UserCreateHandler extends AbstractRequestHandler<UserCreateHandler.Payload> {

  private InkstepStore store;

  public UserCreateHandler(InkstepStore store) {
    super(Payload.class, store);
    this.store = store;
  }

  @Override
  protected Answer processImpl(Payload value, Map<String, String> queryParams) {

    Passphrase passphrase;
    try {
      passphrase = Passphrase.generate();
    } catch (IOException e1) {
      e1.printStackTrace();
      return Answer.empty(BAD_REQUEST);
    }

    User user = new User(value.email, value.email, passphrase.toString());

    int userId = store.putUser(user);

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("user_id", String.valueOf(userId));
      put("user_passphrase", passphrase.toString());
    }};

    return Answer.ok(dataToJson(responseMap));
  }

  class Payload implements Validatable {

    public String email;
    public String username;

    @JsonCreator
    Payload(
      @JsonProperty("user_name") String username,
      @JsonProperty("user_email") String userEmail
    ) {
      this.username = username;
      this.email = userEmail;
    }

    @Override
    public boolean isValid() {
      return true;
    }

  }
}
