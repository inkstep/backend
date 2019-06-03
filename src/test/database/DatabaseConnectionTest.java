package database;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseConnectionTest {

  private final DatabaseConnection dbConnection = new MySQLDatabaseConnection();

  @Before
  public void openConnection() {
    dbConnection.open();
  }

  @After
  public void closeConnection() {
    dbConnection.close();
  }

  @Test
  public void canConnectToDatabase() {
  }

  @Test
  public void canPrepareQuery() {
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
