package handlers;

import java.util.Objects;

public class Answer {
  private int code;
  private String body;

  private Answer(int code, String body) {
    this.code = code;
    this.body = body;
  }

  public static Answer empty(int code) {
    return new Answer(code, "");
  }

  public static Answer ok(String body) {
    return new Answer(200, body);
  }
  
  public String getBody() {
    return this.body;
  }

  public int getCode() {
    return this.code;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Answer)) {
      return false;
    }
    Answer answer = (Answer) o;
    return code == answer.code && Objects.equals(body, answer.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, body);
  }

  @Override
  public String toString() {
    return "{" +
      " code='" + getCode() + "'" +
      ", body='" + getBody() + "'" +
      "}";
  }
}
