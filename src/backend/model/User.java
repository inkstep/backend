package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Validatable {
  public int id;
  public String name;
  public String email;
  public String token;

  @JsonCreator
  public User(@JsonProperty("name") String name, @JsonProperty("email") String email) {
    this.name = name;
    this.email = email;
  }

  public User(String userName, String userEmail, String token) {
    this(userName, userEmail);
    this.token = token;
  }

  public User(String userName, String userEmail, int id, String token) {
    this(userName, userEmail, token);
    this.id = id;
  }

  // TODO(DJRHails): Implement sensible validity check
  @Override public boolean isValid() {
    return true;
  }
}
