package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Studio implements Validatable {
  public String name;
  public int id;

  @JsonCreator
  public Studio(@JsonProperty("name") String name, @JsonProperty("id") int id) {
    this.name = name;
    this.id = id;
  }

  // TODO(DJRHails): Implement sensible validity check
  @Override public boolean isValid() {
    return true;
  }
}
