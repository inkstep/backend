package backend.controllers;

import backend.resources.GreetingResource;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public GreetingResource greetingResource(@RequestParam(value="name", defaultValue="World") String name) {
    return new GreetingResource(counter.getAndIncrement(), name);
  }
}
