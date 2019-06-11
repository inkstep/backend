package handlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Journey;
import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class JourneyRetrieveHandlerTest {

  private final InkstepStore store = mock(InkstepDatabaseStore.class);

  @Test public void jsonEncodedJourneyContainsTheCorrectlyFormattedInfo() {
    int userID = 0;
    int journeyID = 1;
    int artistID = 0;
    int studioID = 0;
    String description = "tattoo.description";
    String size = "tattoo.size";
    String position = "tattoo.position";
    String availability = "0001010";
    String deposit = "deposit";
    int noRefImages = 3;
    int status = 0;

    Journey journey =
      new Journey(journeyID, artistID, studioID, description, size, position, availability, deposit,
        noRefImages, status);
    List<Journey> journeys = new ArrayList<>();
    journeys.add(journey);

    when(store.getJourneysForUserID(userID)).thenReturn(journeys);
    JourneysRetrieveHandler handler = new JourneysRetrieveHandler(store);

    Map<String, String> params = new HashMap<>();
    params.put("user", String.valueOf(userID));

    String body = handler.process(new EmptyPayload(), params).getBody().replace("\"", "");
    System.out.println(body);
    assertThat(body, containsString("size : " + size));
    assertThat(body, containsString("position : " + position));
    assertThat(body, containsString("availability : " + availability));
    assertThat(body, containsString("deposit : " + deposit));
    assertThat(body, containsString("status : " + status));
    assertThat(body, containsString("journeyID : " + journeyID));
    assertThat(body, containsString("userID : " + userID));
    assertThat(body, containsString("artistID : " + artistID));
    assertThat(body, containsString("noRefImages : " + noRefImages));
    assertThat(body, containsString("tattooDesc : " + description));
    assertThat(body, containsString("valid : true"));
  }
}
