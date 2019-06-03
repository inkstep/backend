package endpoints;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import database.DatabaseConnection;
import java.io.IOException;
import java.sql.PreparedStatement;

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
    String passphrase = null;
    try {
      passphrase = UsersEndpoint.generatePassphrase();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertNotNull(passphrase);
  }

  @Test
  public void canGenerateValidPassphrase() throws IOException {
    String passphrase = null;

    passphrase = UsersEndpoint.generatePassphrase();

    int capletters = 0;

    for (int i = 0; i < passphrase.length(); i++) {
      if (Character.isUpperCase(passphrase.charAt(i))) {
        capletters++;
      }
    }

    assertEquals(capletters, UsersEndpoint.PASSPHRASE_WORD_COUNT);
  }
}
