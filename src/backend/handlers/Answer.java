package handlers;

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
    return body;
  }

  public int getCode() {
    return code;
  }
}
