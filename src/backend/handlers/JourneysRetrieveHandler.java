package handlers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import model.Journey;
import model.JourneyStage;
import store.InkstepStore;

public class JourneysRetrieveHandler extends AbstractRequestHandler<EmptyPayload> {

  public static final DateTimeFormatter DATE_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
  public static final ZoneId TIME_ZONE = ZoneId.of("Europe/London");

  public JourneysRetrieveHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
  }

  @Override protected Answer processImpl(EmptyPayload userToBe, Map<String, String> urlParams) {
    System.out.println("Retrieving journeys");

    List<Journey> journeys = store.getJourneysForUserID(Integer.valueOf(urlParams.get("user")));
    LocalDateTime localDateTime = LocalDateTime.now(TIME_ZONE);

    for (Journey journey : journeys) {
      if (journey.stage == JourneyStage.AppointmentBooked) {
        LocalDateTime date = LocalDateTime.parse(journey.bookingDate, DATE_FORMATTER);

        if (localDateTime.isAfter(date)) {
          journey.stage = JourneyStage.Aftercare;
          store.updateStage(journey.journeyID, JourneyStage.Aftercare);
        }
      } else if (journey.stage == JourneyStage.Aftercare) {
        LocalDateTime date = LocalDateTime.parse(journey.bookingDate, DATE_FORMATTER);
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
