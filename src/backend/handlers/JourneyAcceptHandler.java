package handlers;

import static handlers.AbstractRequestHandler.dataToJson;

import email.templates.ArtistResponseTemplate;
import model.Artist;
import model.Journey;
import model.JourneyStage;
import model.User;
import notification.UserNotifier;
import spark.Request;
import spark.Response;
import spark.Route;
import store.InkstepStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class JourneyAcceptHandler implements Route {

  private InkstepStore store;

  public static final DateTimeFormatter STORE_DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

  public static final DateTimeFormatter PARAM_DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  public JourneyAcceptHandler(InkstepStore store) {
    this.store = store;
  }

  @Override
  public Object handle(Request request, Response response) throws Exception {
    Map<String, String> urlParams = bodyParams(request.body());

    int journeyId = Integer.valueOf(request.params(":id"));
    int quoteLower = Integer.valueOf(urlParams.get("quote_lower"));
    int quoteUpper = Integer.valueOf(urlParams.get("quote_upper"));
    String bookingDate = urlParams.get("booking_date");
    String bookingTime = urlParams.get("booking_time");
    String[] time = bookingTime.split("%3A");
    bookingTime = time[0] + ":" + time[1];

    int stage = store.getJourneyStage(journeyId).toCode();

    Journey j = null;
    User u = null;

    if (stage == 0) {
      LocalDateTime date =
        LocalDateTime.parse(
          bookingDate + " " + bookingTime, PARAM_DATE_TIME_FORMATTER);

      store.updateQuote(journeyId, quoteLower, quoteUpper);
      store.offerAppointment(journeyId, date.format(STORE_DATE_TIME_FORMATTER));
      store.updateStage(journeyId, JourneyStage.QuoteReceived);

      // Notify the user's device
      j = store.getJourneyFromId(journeyId);
      u = store.getUserFromID(j.userID);
      UserNotifier un = new UserNotifier(u);
      un.notifyStage(store, j, JourneyStage.QuoteReceived);
    } else {
      System.out.println("Stage not implemented");
      return Answer.ok(dataToJson(false));
    }

    Artist a = store.getArtistFromID(j.artistID);

    String html = new ArtistResponseTemplate().getTemplate();

    html = html.replace("{{ARTIST NAME}}", a.name);
    html = html.replace("{{USER NAME}}", u.name);

    return html;
  }

  Map<String, String> bodyParams(String body) {
    Map<String, String> params = new HashMap<>();

    String[] mappings = body.split("&");

    for (String mapping : mappings) {
      String[] valueKey = mapping.split("=");

      params.put(valueKey[0], valueKey[1]);
    }

    return params;
  }
}
