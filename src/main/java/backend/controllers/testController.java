package backend.controllers;

import backend.resources.testResource;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/test")
  public testResource testResource() {
    return new testResource(counter.incrementAndGet());
  }
}