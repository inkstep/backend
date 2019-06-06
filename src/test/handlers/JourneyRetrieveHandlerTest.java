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

  // TODO(DJRHails): Update handler test for actual data
}
