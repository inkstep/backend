package handlers;

import model.Artist;
import model.Journey;
import model.Studio;
import model.User;

import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;
import handlers.EmptyPayload;

import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JourneyRetrieveHandlerTest {

  private final InkstepStore store = mock(InkstepDatabaseStore.class);

  @Test
  public void dummyJourneyDataCanBeCorrectlyRetrieved() {

    JourneyRetrieveHandler handler = new JourneyRetrieveHandler(store);
    String expectedJson = "[ {\n" +
      "  \"size\" : \"10cm\",\n" +
      "  \"position\" : \"Neck\",\n" +
      "  \"availability\" : \"0000000\",\n" +
      "  \"deposit\" : \"1\",\n" +
      "  \"userID\" : 0,\n" +
      "  \"artistID\" : 0,\n" +
      "  \"noRefImages\" : \"0\",\n" +
      "  \"tattooDesc\" : \"Star\",\n" +
      "  \"valid\" : true\n" +
      "}, {\n" +
      "  \"size\" : \"20cm\",\n" +
      "  \"position\" : \"Chest\",\n" +
      "  \"availability\" : \"0000000\",\n" +
      "  \"deposit\" : \"1\",\n" +
      "  \"userID\" : 0,\n" +
      "  \"artistID\" : 1,\n" +
      "  \"noRefImages\" : \"0\",\n" +
      "  \"tattooDesc\" : \"Flowers\",\n" +
      "  \"valid\" : true\n" +
      "} ]";

    assertEquals(Answer.ok(expectedJson),
      handler.process(new EmptyPayload(), Collections.emptyMap()));
  }
}
