package handlers;

import static model.JourneyBuilder.aJourney;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import handlers.JourneyCreateHandler.Payload;
import model.*;
import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class JourneyCreateHandlerTest {

  private final InkstepStore store = mock(InkstepDatabaseStore.class);

  @Test public void aInvalidNewJourneyReturnsBadRequest() {
    Payload newJourney = new Payload(
      0, // ArtistID
      0, // StudioID
      "tattoo.description", "tattoo.size", "tattoo.position", "tattoo.style",
      "00010", // Bad Avaliability Bitmap
      0 // Number of Reference Images
    );

    JourneyCreateHandler handler = new JourneyCreateHandler(store);
    assertEquals(Answer.code(AbstractRequestHandler.BAD_REQUEST),
      handler.process(newJourney, Collections.emptyMap()));
  }

  @Test public void aNewJourneyIsCorrectlyCreated() {
    Payload payload = new Payload(
      0, // ArtistID
      0, // StudioID
      "tattoo.description", "tattoo.size", "tattoo.position", "tattoo.style",
      "0001010", // Avaliability Bitmap
      0 // Number of Reference Images
    );

    String expectedJson = "{\n" + "  \"journey_id\" : \"0\"\n" + "}";

    Journey journey =
      aJourney()
      .withID(-1)
      .withUserID(payload.userID)
      .withArtistID(payload.artistID)
      .withTattooDesc(payload.tattooDesc)
      .withSize(payload.size)
      .withPosition(payload.position)
      .withStyle(payload.style)
      .withAvailability(payload.availability)
      .withNoRefImages(payload.noRefImages)
      .withQuoteLower(-1)
      .withQuoteUpper(-1)
      .withStage(0)
      .withBookingDate(null)
      .build();

    when(store.createJourney(journey)).thenReturn(0);
    when(store.getArtistFromID(0))
      .thenReturn(new Artist("artist.name", "artist.email", "artist.profileurl", 0, 1));
    when(store.getStudioFromID(0)).thenReturn(new Studio("studio.name", 1));
    when(store.getUserFromID(0)).thenReturn(new User("username", "user.emai", "user.token"));

    JourneyCreateHandler handler = new JourneyCreateHandler(store);
    assertEquals(Answer.ok(expectedJson), handler.process(payload, Collections.emptyMap()));
  }
}
