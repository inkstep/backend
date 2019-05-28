package backend.resources;

public class testResource {

  private final long id;

  public testResource(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return "Go away! not setup yet!";
  }
}