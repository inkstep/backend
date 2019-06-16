package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import handlers.JourneyAcceptHandler.Payload;
import model.Journey;
import model.JourneyStage;
import model.User;
import model.Validatable;
import notification.UserNotifier;
import store.InkstepStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class JourneyAcceptHandler extends AbstractRequestHandler<Payload> {

  private InkstepStore store;

  public static final DateTimeFormatter STORE_DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

  public static final DateTimeFormatter PARAM_DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  public JourneyAcceptHandler(InkstepStore store) {
    super(Payload.class, store);
    this.store = store;
  }

  @Override
  protected Answer processImpl(Payload payload, Map<String, String> urlParams) {
    int journeyId = Integer.valueOf(urlParams.get(":id"));
    int stage = store.getJourneyStage(journeyId).toCode();

    if (stage == 0) {
      LocalDateTime date =
        LocalDateTime.parse(
          payload.bookingDate + " " + payload.bookingTime, PARAM_DATE_TIME_FORMATTER);

      store.updateQuote(journeyId, payload.quoteLower, payload.quoteUpper);
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

  static class Payload implements Validatable {
    final int quoteLower;
    final int quoteUpper;
    final String bookingDate;
    final String bookingTime;

    @JsonCreator
    public Payload(
      @JsonProperty("quote_lower") int quoteLower,
      @JsonProperty("quote_upper") int quoteUpper,
      @JsonProperty("booking_date") String bookingDate,
      @JsonProperty("booking_time") String bookingTime) {
      this.quoteLower = quoteLower;
      this.quoteUpper = quoteUpper;
      this.bookingDate = bookingDate;
      this.bookingTime = bookingTime;
    }

    // TODO(DJRHails): Add proper validation for Journey Payload
    @Override
    public boolean isValid() {
      return true;
    }

    @Override
    public String toString() {
      return "Accept {" +
        ", quoteLower='" + quoteLower + "'" +
        ", quoteUpper='" + quoteUpper + "'" +
        ", bookingDate='" + bookingDate + "'" +
        ", bookingTime='" + bookingTime + "'" +
        "}";
    }
  }
}
