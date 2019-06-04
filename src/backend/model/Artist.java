package model;

public class Artist {
  public int id;
  public int studioID;
  public String name;
  public String email;

  public Artist(String name, String email, int studioID) {
    this.name = name;
    this.email = email;
    this.studioID = studioID;
  }

  public Artist(String name, String email, int studioID, int id) {
    this(name, email, studioID);
    this.id = id;
  }
}
