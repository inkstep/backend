package handlers;

import handlers.JourneyCreateHandler.Payload;
import model.Artist;
import model.Journey;
import model.Studio;
import model.User;

import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JourneyCreateHandlerTest {

  private final InkstepStore store = mock(InkstepDatabaseStore.class);

  @Test
  public void aInvalidNewJourneyReturnsBadRequest() {
    Payload newJourney = new Payload(
      0, // ArtistID
        0, // StudioID
        "tattoo.description", "tattoo.size", "tattoo.position", "00010", // Bad Avaliability Bitmap
          0 // Number of Reference Images
    );
    JourneyCreateHandler handler = new JourneyCreateHandler(store);
    assertEquals(Answer.code(AbstractRequestHandler.BAD_REQUEST), handler.process(newJourney, Collections.emptyMap()));
  }

  @Test
  public void aNewJourneyIsCorrectlyCreated() {
    Payload payload = new Payload(
      0, // ArtistID
        0, // StudioID
        "tattoo.description", "tattoo.size", "tattoo.position", "0001010", // Avaliability Bitmap
          0 // Number of Reference Images
    );

    String expectedJson = "{\n" +
      "  \"journey_id\" : \"0\"\n" +
      "}";

    Journey journey = new Journey(
      -1,
      payload.userID,
      payload.artistID,
      payload.tattooDesc,
      payload.size,
      payload.position,
      payload.availability,
      payload.noRefImages,
      -1,
      -1,
      0,
      null
    );

    when(store.createJourney(journey)).thenReturn(0);
    when(store.getArtistFromID(0)).thenReturn(new Artist("artist.name", "artist.email", 0, 1));
    when(store.getStudioFromID(0)).thenReturn(new Studio("studio.name", 1));
    when(store.getUserFromID(0)).thenReturn(new User("username", "user.emai", "user.token"));

    JourneyCreateHandler handler = new JourneyCreateHandler(store);
    assertEquals(Answer.ok(expectedJson), handler.process(payload, Collections.emptyMap()));
  }
}
