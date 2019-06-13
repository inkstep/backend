package handlers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Journey;
import model.JourneyStage;
import store.InkstepStore;

import java.util.Map;

public class JourneyRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneyRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    Journey journey = store.getJourneyFromId(Integer.valueOf(urlParams.get(":id")));

    if (journey.stage == JourneyStage.AppointmentBooked) {
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
      LocalDateTime date = LocalDateTime.parse(journey.bookingDate, dateFormatter);
      LocalDateTime localDateTime = LocalDateTime.now();

      if (localDateTime.isAfter(date)) {
        journey.stage = JourneyStage.Aftercare;
        store.updateStage(journey.journeyID, JourneyStage.Aftercare);
      }
    }

    return Answer.ok(dataToJson(journey));
  }
}
