package handlers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import email.JourneyMail;
import model.JourneyStage;
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

    if (newStage == JourneyStage.WaitingAppointmentOffer) {
      new JourneyMail(
              store,
              store.getJourneyFromId(journeyId),
              null
      ).sendQuoteAcceptedEmail();
    }

    Map<String, String> responseMap = new HashMap<String, String>() {{
      put("", String.valueOf(3));
    }};

    return Answer.ok(dataToJson(responseMap));
  }

  static class Payload implements Validatable {

    private int stage;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)Payload(
      @JsonProperty("stage") int newStage) {
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
