package handlers;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import handlers.UserEmailUpdateHandler.Payload;
import model.Validatable;
import store.InkstepStore;

public class UserEmailUpdateHandler extends AbstractRequestHandler<Payload> {

  public UserEmailUpdateHandler(InkstepStore store) {
    super(Payload.class, store);
  }

  @Override protected Answer processImpl(Payload payload, Map<String, String> urlParams) {
    int userId = Integer.valueOf(urlParams.get(":id"));

    String email = payload.getEmail();

    store.updateEmail(userId, email);

    return Answer.ok(dataToJson(true));
  }

  static class Payload implements Validatable {

    private String email;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    Payload(
      @JsonProperty("Email") String newEmail) {
      this.email = newEmail;
    }

    public String getEmail() {
      return email;
    }

    @Override
    public boolean isValid() {
      return true;
    }
  }
}


