package handlers;

import model.Journey;
import model.JourneyStage;
import model.User;
import notification.UserNotifier;
import store.InkstepStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class JourneyAcceptHandler extends AbstractRequestHandler<EmptyPayload> {

  private InkstepStore store;

  public static final DateTimeFormatter STORE_DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

  public static final DateTimeFormatter PARAM_DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  public JourneyAcceptHandler(InkstepStore store) {
    super(EmptyPayload.class, store);
    this.store = store;
  }

  @Override
  protected Answer processImpl(EmptyPayload payload,
                               Map<String, String> urlParams) {
    int journeyId = Integer.valueOf(urlParams.get(":id"));
    int quoteLower = Integer.valueOf(urlParams.get("quote_lower"));
    int quoteUpper = Integer.valueOf(urlParams.get("quote_upper"));
    String bookingDate = urlParams.get("booking_date");
    String bookingTime = urlParams.get("booking_time");

    int stage = store.getJourneyStage(journeyId).toCode();

    if (stage == 0) {
      LocalDateTime date =
        LocalDateTime.parse(
          bookingDate + " " + bookingTime, PARAM_DATE_TIME_FORMATTER);

      store.updateQuote(journeyId, quoteLower, quoteUpper);
      store.offerAppointment(journeyId, date.format(STORE_DATE_TIME_FORMATTER));
      store.updateStage(journeyId, JourneyStage.QuoteReceived);

      // Notify the user's device
      Journey j = store.getJourneyFromId(journeyId);
      User u = store.getUserFromID(j.userID);
      UserNotifier un = new UserNotifier(u);
      un.notifyStage(j, JourneyStage.QuoteReceived);
    } else {
      System.out.println("Stage not implemented");
      Answer.ok(dataToJson(false));
    }

    return Answer.ok(dataToJson(true));
  }
}
