package model;

public class Artist {
  final String name;
  final String email;
  final Studio studio;

  public Artist(String name, String email, Studio studio) {
    this.name = name;
    this.email = email;
    this.studio = studio;
  }
}
