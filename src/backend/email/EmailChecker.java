package email;


import email.JavaEmail;
import email.JavaMessage;
import model.Journey;
import model.JourneyStage;
import model.User;
import notification.UserNotifier;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class EmailChecker implements Runnable {

  private JavaEmail javaEmail = new JavaEmail();
  private InkstepStore store = new InkstepDatabaseStore();

  @Override
  public void run() {
    int count = 0;
    while (true) {
      System.out.println("Checking emails for the " + count + " time ...");
      count++;

      JavaMessage[] messages = javaEmail.receiveEmail();

      for (JavaMessage message : messages) {
        try {
          System.out.println("Message : " + message.getContent());
          String[] subject = message.getSubject().split("[ \n\r]");
          int journeyId = Integer.parseInt(subject[subject.length - 1]);
          int stage = store.getJourneyStage(journeyId).toCode();

          switch (stage) {
            case 0:
              String quote = message.getContent().split("[ \n\r]")[0];
              String dateTime = message.getContent().split("[ \n\r]")[1] + " "
                      + message.getContent().split("[ \n\r]")[2];
              System.out.println(quote);
              System.out.println(dateTime);

              store.updateQuote(journeyId, quote.split("-")[0], quote.split("-")[1]);
              store.offerAppointment(journeyId, dateTime);
              store.updateStage(journeyId, JourneyStage.QuoteReceived);

              // Notify the user's device
              Journey j = store.getJourneyFromId(journeyId);
              User u = store.getUserFromID(j.userID);
              UserNotifier un = new UserNotifier(u);
              un.notifyStage(j, JourneyStage.QuoteReceived);

              break;
            default:
              System.out.println("Stage not implemented");
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      /* Sleep for one minute */
      try {
        Thread.sleep(1000 * 10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
