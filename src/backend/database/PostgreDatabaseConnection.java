package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreDatabaseConnection implements DatabaseConnection {

  private Connection connection;

  public PostgreDatabaseConnection() {
    //connection = database.getConnection();
  }

  @Override
  public PreparedStatement prepareStatement(String sql) {
    try {
      return connection.prepareStatement(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
