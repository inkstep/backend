package handlers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.User;
import model.Validatable;
import store.InkstepStore;

public class UserCreateHandler extends AbstractRequestHandler<UserCreateHandler.Payload> {

  private InkstepStore store;

  public UserCreateHandler(InkstepStore store) {
    super(Payload.class, store);
    this.store = store;
  }

  @Override protected Answer processImpl(Payload value, Map<String, String> queryParams) {
    User user = new User(value.username, value.email, value.token);

    int userId = store.putUser(user);

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("user_id", String.valueOf(userId));
    }};

    return Answer.ok(dataToJson(responseMap));
  }

  static class Payload implements Validatable {

    public String email;
    public String username;
    public String token;

    @JsonCreator Payload(@JsonProperty("user_name") String username,
      @JsonProperty("user_email") String userEmail, @JsonProperty("token") String token) {
      this.username = username;
      this.email = userEmail;
      this.token = token;
    }

    @Override public boolean isValid() {
      return true;
    }
  }
}
