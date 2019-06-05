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
    assertEquals(Answer.ok(
      "["
      + "{\"size\":\"10cm\",\"position\":\"Neck\",\"availability\":\"0000000\",\"deposit\":\"1\",\"userID\":0,\"artistID\":0,\"noRefImages\":\"0\",\"tattooDesc\":\"Star\",\"valid\":true}"
      + ","
      + "{\"size\":\"20cm\",\"position\":\"Chest\",\"availability\":\"0000000\",\"deposit\":\"1\",\"userID\":0,\"artistID\":1,\"noRefImages\":\"0\",\"tattooDesc\":\"Flowers\",\"valid\":true}"
      + "]"),
      handler.process(new EmptyPayload(), Collections.emptyMap()));
  }
}
