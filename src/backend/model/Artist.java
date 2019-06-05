package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Artist {
  public int id;
  public int studioID;
  public String name;
  public String email;

  public Artist(String name, String email, int studioID) {
    this.name = name;
    this.email = email;
    this.studioID = studioID;
  }

  @JsonCreator public Artist(@JsonProperty("name") String name, @JsonProperty("email") String email,
    @JsonProperty("studioID") int studioID, @JsonProperty("id") int id) {
    this(name, email, studioID);
    this.id = id;
  }
}
