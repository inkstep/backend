package handlers;

import model.Journey;
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
                "",
                "",
                0
        );
        assertTrue(newJourney.isValid());

        when(store.createJourney(newJourney)).thenReturn(0);

        JourneyCreateHandler handler = new JourneyCreateHandler(store);
        assertEquals(Answer.ok("0"), handler.process(newJourney, Collections.emptyMap(), false));
    }
}
