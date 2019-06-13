package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import email.JourneyMail;
import model.Journey;
import model.JourneyStage;
import model.User;
import model.Validatable;
import store.InkstepStore;

import java.util.*;

public class JourneyUpdateHandler
  extends AbstractRequestHandler<JourneyUpdateHandler.Payload> {

  public JourneyUpdateHandler(InkstepStore store) {
    super(Payload.class, store);
  }

  @Override
  protected Answer processImpl(Payload request, Map<String, String> urlParams) {
    int journeyId = Integer.valueOf(urlParams.get(":id"));
    JourneyStage newStage = JourneyStage.values()[request.getStage()];

    store.updateStage(journeyId, newStage);

    Journey j = store.getJourneyFromId(journeyId);
    User u = store.getUserFromID(j.userID);

    // See documentation on defining a message payload.
    Message message = Message.builder()
      .putData("score", "850")
      .putData("time", "2:45")
      .setToken(u.token)
      .build();

    // Send a message to the device corresponding to the provided
    // registration token.
    String response = null;
    try {
      response = FirebaseMessaging.getInstance().send(message);
    } catch (FirebaseMessagingException e) {
      e.printStackTrace();
    }
    System.out.println("Sent notification due to update stage: " + response);

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("JourneyID", String.valueOf(journeyId));
    }};

    return Answer.ok(dataToJson(responseMap));
  }

  static class Payload implements Validatable {

    private int stage;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    Payload(
      @JsonProperty("Stage") int newStage) {
      this.stage = newStage;
    }

    public int getStage() {
      return stage;
    }

    @Override
    public boolean isValid() {
      return true;
    }
  }
}
