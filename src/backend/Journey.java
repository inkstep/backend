public class Journey {

  public static Object newJourney(String name, String artistName,
      String artistEmail, String tattoo, String size, String position,
      String description) {
    String emailTemplate = "Hi, %s \n You have received a tattoo request from"
        + " %s \n TYPE %s \n SIZE: %s \n POSITION: %s \n DESCRIPTION: %S";

    String toSend = String.format(emailTemplate, artistName, name, tattoo,
        size, position, description);

    System.out.println(toSend);

    return "Email sent: " + toSend;
  }
}
