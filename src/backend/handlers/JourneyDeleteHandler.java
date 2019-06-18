package handlers;

import java.util.HashMap;
import java.util.Map;

import model.Journey;
import model.JourneyStage;
import notification.WaiterNotifier;
import store.InkstepStore;

public class JourneyDeleteHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyDeleteHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload request, Map<String, String> urlParams) {
    int journeyId = Integer.valueOf(urlParams.get(":id"));

    Journey removedJourney = store.removeJourney(journeyId);

    if (removedJourney == null) {
      return Answer.code(Answer.BAD_USER);
    }

    if (removedJourney.stage.toCode() == JourneyStage.AppointmentBooked.toCode()
      || removedJourney.stage.toCode() == JourneyStage.AppointmentOfferReceived.toCode()
      || removedJourney.stage.toCode() == JourneyStage.QuoteReceived.toCode()) {
      WaiterNotifier waiterNotifier = new WaiterNotifier(store).newSlotUsing(removedJourney);

      Map<String, String> responseMap = new HashMap<String, String>() {{
        put("success", String.valueOf(waiterNotifier.getSuccesses()));
        put("failure", String.valueOf(waiterNotifier.getFailures()));
      }};
      return Answer.ok(dataToJson(responseMap));
    }

    return Answer.ok("");
  }

}
