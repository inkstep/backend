package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Validable {
  public String name;
  public String email;
  public String passphrase;
  public int id;

  @JsonCreator
  public User(@JsonProperty("name") String name, @JsonProperty("email") String email) {
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

  // TODO(DJRHails): Implement sensible validity check
  @Override
  public boolean isValid() {
    return true;
  }
}
