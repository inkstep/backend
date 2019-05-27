package backend.resources;

public class GreetingResource {

  private final long id;
  private final String name;

  public GreetingResource(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return "Hello, " + name;
  }
}
