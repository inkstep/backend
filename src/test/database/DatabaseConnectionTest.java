package database;

import org.junit.Test;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class DatabaseConnectionTest {

  private final InkstepStore store = new InkstepDatabaseStore();

  @Test public void canConnectToDatabase() {
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
