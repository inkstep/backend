package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Artist {
  public int id;
  public int studioID;
  public String name;
  public String email;

  @JsonCreator public Artist(@JsonProperty("name") String name, @JsonProperty("email") String email,
    @JsonProperty("studioID") int studioID, @JsonProperty("artistId") int id) {
    this.name = name;
    this.studioID = studioID;
    this.email = email;
    this.id = id;
  }
}
