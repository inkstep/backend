package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Studio implements Validatable {
  public String name;

  @JsonCreator
  public Studio(@JsonProperty("name") String name) {
    this.name = name;
  }

  // TODO(DJRHails): Implement sensible validity check
  @Override public boolean isValid() {
    return true;
  }
}
