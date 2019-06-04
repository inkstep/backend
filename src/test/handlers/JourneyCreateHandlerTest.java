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

    }

    @Test
    public void aNewJourneyIsCorrectlyCreated() {
        Journey newJourney = new Journey(
                0,
                0,
                "",
                "",
                "",
                "0001010",
                "",
                0
        );

        when(store.createJourney(newJourney)).thenReturn(0);
        when(store.getArtistFromID(0)).thenReturn(new Artist("artist.name", "artist.email", 0));
        when(store.getStudioFromID(0)).thenReturn(new Studio("studio.name"));
        when(store.getUserFromID(0)).thenReturn(new User("username", "user.emai", "user.passphrase"));

        JourneyCreateHandler handler = new JourneyCreateHandler(store);
        assertEquals(Answer.ok("0"), handler.process(newJourney, Collections.emptyMap()));
    }
}
