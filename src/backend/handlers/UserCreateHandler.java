package handlers;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import model.Validable;
import model.Passphrase;
import store.InkstepStore;

/**
 * UserCreateHandler
 */
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

    User user = new User(value.useremail, value.useremail, passphrase.toString());

    int userId = store.putUser(user);

    String jsonOut;
    try {
      Map<String, String> responseMap = Map.of("user_id", String.valueOf(userId), "user_passphrase", passphrase.toString());
      jsonOut = new ObjectMapper().writeValueAsString(responseMap);
    } catch (IOException e) {
      e.printStackTrace();
      return Answer.empty(BAD_REQUEST);
    }

    return Answer.ok(jsonOut);
  }

  class Payload implements Validable {

    public String useremail;
    public String username;

    @JsonCreator
    Payload(@JsonProperty("user_name") String username, @JsonProperty("user_email") String userEmail) {
      this.username = username;
      this.useremail = userEmail;
    }

    @Override
    public boolean isValid() {
      return true;
    }

  }
}