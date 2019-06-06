package handlers;

import model.Artist;
import model.Journey;
import model.Studio;
import model.User;

import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;

import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JourneyCreateHandlerTest {

  private final InkstepStore store = mock(InkstepDatabaseStore.class);

  @Test
  public void aInvalidNewJourneyReturnsBadRequest() {
    Journey newJourney = new Journey(1, // JourneyID
      0, // ArtistID
        0, // StudioID
        "tattoo.description", "tattoo.size", "tattoo.position", "00010", // Bad Avaliability Bitmap
        "deposit", 0 // Number of Reference Images
    );
    JourneyCreateHandler handler = new JourneyCreateHandler(store);
    assertEquals(Answer.empty(AbstractRequestHandler.BAD_REQUEST), handler.process(newJourney, Collections.emptyMap()));
  }

  @Test
  public void aNewJourneyIsCorrectlyCreated() {
    Journey newJourney = new Journey(1, // JourneyID
      0, // ArtistID
        0, // StudioID
        "tattoo.description", "tattoo.size", "tattoo.position", "0001010", // Avaliability Bitmap
        "deposit", 0 // Number of Reference Images
    );

    String expectedJson = "{\n" +
      "  \"journey_id\" : \"0\"\n" +
      "}";

    when(store.createJourney(newJourney)).thenReturn(0);
    when(store.getArtistFromID(0)).thenReturn(new Artist("artist.name", "artist.email", 0));
    when(store.getStudioFromID(0)).thenReturn(new Studio("studio.name"));
    when(store.getUserFromID(0)).thenReturn(new User("username", "user.emai", "user.passphrase"));

    JourneyCreateHandler handler = new JourneyCreateHandler(store);
    assertEquals(Answer.ok(expectedJson), handler.process(newJourney, Collections.emptyMap()));
  }
}
