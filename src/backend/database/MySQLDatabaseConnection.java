package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDatabaseConnection implements DatabaseConnection {

  private static final String DB_URL =
    "jdbc:mysql://inkstepdb.cqjzj0pfmjrn.eu-west-2.rds.amazonaws.com:3306/inkstep";
  private static final String DB_USERNAME = "docg1827107group";

  private Connection connection;

  private boolean connected;

  // Return a connection to the database with the following info
  /*public static synchronized Connection getConnection() {
    Connection c = null;
    try {
      Class.forName("org.postgresql.Driver");
      String host = "db.doc.ic.ac.uk:5432";
      String database = "g1827107_u";
      String username = "g1827107_u";
      String password = System.getenv("database_password");
      String url = "jdbc:postgresql://" + host + "/" + database;
      String driver = "org.postgresql.Driver";
      Class.forName(driver);

      c = DriverManager.getConnection(url, username,
        password);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }

    System.out.println("Opened database successfully");

    return c;
  }*/

  @Override public void open() {
    if (connected) {
      return;
    }
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, "root");
      connected = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public void close() {
    if (!connected) {
      return;
    }
    try {
      connection.close();
      connected = false;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void dos() {
    //connection.prepareStatement(sql)
    if (!connected) {
      return;
    }
    try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("select * from emp");
      while (rs.next()) {
        System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
