package database;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

import model.Journey;
import model.User;
import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class DatabaseStoreTest {

  private final InkstepStore store = new InkstepDatabaseStore();

  @Test public void cannotInjectIntoInsertQuery() {
    String payload = "',(select version())) -- ";
    String username = "name-lol";

    User user = new User(username, payload, "fake_token");
    int id = store.putUser(user);
    user = store.getUserFromID(id);

    assertEquals(username, user.name);
    assertEquals(payload, user.email);

    // Clean up after
    store.removeUser(user.id);
  }

  @Test public void canAddAndRemoveJourney() {
    Journey journey = new Journey(-1, 0, 0, null, null, null, null, 0, 0, 0, 0, null);
    int journeyId = store.createJourney(journey);

    // Make sure journey was added
    Journey journeyFromId = store.getJourneyFromId(journeyId);
    assertEquals(journey.userID, journeyFromId.userID);
    assertEquals(journey.artistID, journeyFromId.artistID);
    assertEquals(journey.tattooDesc, journeyFromId.tattooDesc);
    assertEquals(journey.size, journeyFromId.size);
    assertEquals(journey.position, journeyFromId.position);
    assertEquals(journey.availability, journeyFromId.availability);
    assertEquals(journey.noRefImages, journeyFromId.noRefImages);
    assertEquals(journey.stage, journeyFromId.stage);
    assertEquals(journey.bookingDate, journeyFromId.bookingDate);

    // Make sure journey can be removed
    store.removeJourney(journeyId);
    assertNull(store.getJourneyFromId(journeyId));
  }

  @Test public void canPrepareQuery() {
    //    Users.setDatabaseConnection(databaseConnectionMock);
    //
    //    when(databaseConnectionMock.prepareStatement(
    //      "insert into users (user_name, user_passphrase) values (?, ?)"
    //    )).thenReturn(preparedStatementMock);
    //
    //    Users.putUser("Jimmy");
    //
    //    verify(preparedStatementMock).setString(1, "Jimmy");
    //    verify(preparedStatementMock).setString(eq(1), anyString());
    //
    //    verify(preparedStatementMock).executeUpdate();
    //    verify(databaseConnectionMock).close();
  }
}
