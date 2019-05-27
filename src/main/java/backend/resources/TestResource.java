package backend.resources;

import java.util.concurrent.atomic.AtomicLong;

public class TestResource {
  private static AtomicLong id;

  public long getId() {
    return id.getAndIncrement();
  }

  public String getContent() {
    return "Go away, not setup yet!";
  }
}
