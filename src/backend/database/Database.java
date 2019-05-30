package database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.stream.Stream;

public class Database {

  public static final String PASSWORD_FILE_PATH = "password.txt";

  /* Return a connection to the database with the following info */
  public synchronized static Connection getConnection() {
    Connection c = null;
    try {
      Class.forName("org.postgresql.Driver");
      String host = "db.doc.ic.ac.uk:5432";
      String database = "g1827107_u";
      String username = "g1827107_u";
      Stream<String> lines = Files.lines(Paths.get(PASSWORD_FILE_PATH));
      String password = lines.findFirst().get();
      String url = "jdbc:postgresql://" + host + "/" + database;
      String driverJDBC = "org.postgresql.Driver";
      Class.forName(driverJDBC);

      c = DriverManager.getConnection(url, username,
          password);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName()+": "+e.getMessage());
      System.exit(0);
    }

    System.out.println("Opened database successfully");

    return c;
  }
}
