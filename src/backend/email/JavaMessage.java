package email;

public class JavaMessage {
  private final String subject;
  private final String from;
  private final String content;

  public JavaMessage(String subject, String from, String content) {
    this.subject = subject;
    this.from = from;
    this.content = content;
  }

  public String getSubject() {
    return subject;
  }

  public String getFrom() {
    return from;
  }

  public String getContent() {
    return content;
  }
}
