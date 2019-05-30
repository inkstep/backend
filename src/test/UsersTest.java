import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Database.DatabaseConnection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UsersTest {
  @Mock
  DatabaseConnection databaseConnectionMock;

  @Mock
  PreparedStatement preparedStatementMock;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void canGeneratePassphrase() {
    Users.setDatabaseConnection(databaseConnectionMock);

    String passphrase = null;
    try {
      passphrase = Users.generatePassphrase();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertNotNull(passphrase);
  }

  @Test
  public void canGenerateValidPassphrase() throws IOException {
    Users.setDatabaseConnection(databaseConnectionMock);

    String passphrase = null;

    passphrase = Users.generatePassphrase();

    int capletters = 0;

    for (int i = 0; i < passphrase.length(); i++) {
      if (Character.isUpperCase(passphrase.charAt(i))) {
        capletters++;
      }
    }

    assertEquals(capletters, Users.PASSPHRASE_WORD_COUNT);
  }

  @Test
  public void canGenerateValidSQL() throws SQLException {
    Users.setDatabaseConnection(databaseConnectionMock);

    when(databaseConnectionMock.prepareStatement(
        "insert into users (user_name, user_passphrase) values (?, ?)"
    )).thenReturn(preparedStatementMock);

    Users.putUser("Jimmy");

    verify(preparedStatementMock).setString(1, "Jimmy");
    verify(preparedStatementMock).setString(eq(1), anyString());

    verify(preparedStatementMock).executeUpdate();
    verify(databaseConnectionMock).close();
  }
}