package handlers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Journey;
import model.JourneyStage;
import store.InkstepStore;

import java.util.List;
import java.util.Map;

public class JourneysRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public JourneysRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    System.out.println("Retrieving journeys");

    List<Journey> journeys = store.getJourneysForUserID(Integer.valueOf(urlParams.get("user")));

    for (Journey journey : journeys) {
      if (journey.stage == JourneyStage.AppointmentBooked) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime date = LocalDateTime.parse(journey.bookingDate, dateFormatter);
        LocalDateTime localDateTime = LocalDateTime.now();

        if (localDateTime.isAfter(date)) {
          journey.stage = JourneyStage.Aftercare;
          store.updateStage(journey.journeyID, JourneyStage.Aftercare);
        }
      } else if (journey.stage == JourneyStage.Aftercare) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime date = LocalDateTime.parse(journey.bookingDate, dateFormatter);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime doneDate = date.plusDays(31);

        if (localDateTime.isAfter(doneDate)) {
          journey.stage = JourneyStage.Healed;
          store.updateStage(journey.journeyID, JourneyStage.Healed);
        }
      }
    }

    return Answer.ok(dataToJson(journeys));
  }
}
