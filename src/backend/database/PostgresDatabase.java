package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDatabase {

  /* Return a connection to the database with the following info */
  public static synchronized Connection getConnection() {
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
  }
}
