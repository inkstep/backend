package backend.controllers;

import backend.resources.VersionResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

  @RequestMapping("/")
  public VersionResource versionResource() {
    return new VersionResource();
  }
}
