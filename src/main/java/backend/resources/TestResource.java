package backend.resources;

public class TestResource {

  private final long id;

  public TestResource(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return "Go away! not setup yet!";
  }
}