package handlers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Validatable;
import store.InkstepStore;

public class UserUpdateHandler extends AbstractRequestHandler<UserUpdateHandler.Payload> {

    public UserUpdateHandler(InkstepStore store) {
        super(Payload.class, store);
    }

    @Override
    protected Answer processImpl(Payload request, Map<String, String> urlParams) {
        int userId = Integer.valueOf(urlParams.get(":id"));

        String newToken = request.getToken();

        store.updateToken(userId, newToken);

        Map<String, String> responseMap = new HashMap<String, String>() {{
            put("UserID", String.valueOf(userId));
        }};

        return Answer.ok(dataToJson(responseMap));
    }

    static class Payload implements Validatable {

        private String token;

        @JsonCreator
        @JsonIgnoreProperties(ignoreUnknown = true)
        Payload(
                @JsonProperty("Token") String newToken) {
            this.token = newToken;
        }

        public String getToken() {
            return token;
        }

        @Override
        public boolean isValid() {
            return true;
        }
    }
}
