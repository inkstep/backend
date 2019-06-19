package handlers;

import java.util.Objects;

import spark.Response;

public class StringAnswer extends Answer {
  private int code;
  private String body;

  public StringAnswer(int code, String body) {
    this.code = code;
    this.body = body;
  }

  @Override public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof StringAnswer)) {
      return false;
    }
    StringAnswer answer = (StringAnswer) o;
    return code == answer.code && Objects.equals(body, answer.body);
  }

  @Override public int hashCode() {
    return Objects.hash(code, body);
  }

  @Override public String toString() {
    return "{" + " code='" + code + "'" + ", body='" + body + "'" + "}";
  }

  @Override
  public void update(Response response) {
    response.status(code);
    response.type("application/json");
    response.body(body);
  }
}
