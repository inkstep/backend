package backend.resources;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VersionResource {

  private static final String VERSION_PATH = "version.properties";

  public String getContent() throws IOException {
      /* Grabs version number and formats it correctly */
      String version = new String(Files.readAllBytes(Paths.get(VERSION_PATH)));
      version = version.substring(0, version.length() - 1);

      return "Version: " + version;
    }
}
