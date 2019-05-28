package backend.controllers;

import backend.resources.VersionResource;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

  private AtomicLong counter = new AtomicLong();

  /* Returns a versionResource when accessed through root endpoint */
  @RequestMapping("/")
  public VersionResource versionResource(@RequestParam(value="name", defaultValue="World")
      String name) {
    return new VersionResource();
  }
}
