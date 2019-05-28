package backend.controllers;

import backend.resources.TestResource;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/test")
  public TestResource testResource() {
    return new TestResource(counter.incrementAndGet());
  }
}