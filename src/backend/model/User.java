package model;

public class User {
  public String name;
  public String email;
  public String passphrase;
  public int id;

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public User(String userName, String userEmail, String passphrase) {
    this(userName, userEmail);
    this.passphrase = passphrase;
  }

  public User(String userName, String userEmail, String passphrase, int id) {
    this(userName, userEmail, passphrase);
    this.id = id;
  }
}
