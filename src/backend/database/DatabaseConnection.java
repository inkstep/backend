package database;

import java.sql.PreparedStatement;

public interface DatabaseConnection {

  PreparedStatement prepareStatement(String s);

  void close();
}
