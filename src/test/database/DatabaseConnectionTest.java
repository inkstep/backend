package database;

import static junit.framework.TestCase.assertEquals;

import model.User;
import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class DatabaseConnectionTest {

  private final InkstepStore store = new InkstepDatabaseStore();

  @Test public void cannotInjectIntoInsertQuery() {
    String payload = "','passphrase-lol',(select version())) -- ";
    String username = "name-lol";

    User user = new User(username, payload, "fake_token");
    int id = store.putUser(user);
    user = store.getUserFromID(id);

    assertEquals(username, user.name);
    assertEquals(payload, user.email);
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
