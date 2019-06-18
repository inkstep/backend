package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.*;
import notification.UserNotifier;
import notification.WaiterNotifier;
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

    JourneyStage newStage = JourneyStage.values()[request.getStage()]; // TODO(mm5917): null pointer exception
    // TODO(DJRHails): Should go in valid check of payload
    if (request.getStage() > JourneyStage.values().length
      || request.getStage() < 0) {
      return Answer.code(400);
    }

    Journey j = store.getJourneyFromId(journeyId);
    if (j == null) {
      return Answer.code(Answer.BAD_USER);
    }

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("journey_identifier", String.valueOf(journeyId));
    }};

    if (newStage.toCode() == JourneyStage.WaitingList.toCode()) {
      WaiterNotifier waiterNotifier = new WaiterNotifier(store).newSlotUsing(j);
      responseMap.put("notified", String.valueOf(waiterNotifier.notified.toString()));
    }

    User u = store.getUserFromID(j.userID);

    if (newStage.toCode() == JourneyStage.AppointmentBooked.toCode()
      && j.stage.toCode() == JourneyStage.WaitingList.toCode()) {
      WaiterNotifier waiterNotifier = new WaiterNotifier(store).slotFilledBy(u, j);
      responseMap.put("revoked", String.valueOf(waiterNotifier.notified.toString()));
    }

    store.updateStage(journeyId, newStage);
    UserNotifier un = new UserNotifier(u);
    Artist a = store.getArtistFromID(j.artistID);
    boolean successfulNotification = un.notifyStage(a, j, j.stage);
    responseMap.put("original_journey_notified", String.valueOf(successfulNotification));

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
      return stage < JourneyStage.values().length
        && stage >= 0;
    }
  }
}
