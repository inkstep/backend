package handlers;


import email.JavaEmail;
import email.JavaMessage;
import model.JourneyStage;
import store.InkstepDatabaseStore;
import store.InkstepStore;

public class EmailHandler implements Runnable {

  private JavaEmail javaEmail = new JavaEmail();
  private InkstepStore store = new InkstepDatabaseStore();

  @Override
  public void run() {
    while (true) {
      JavaMessage[] messages = javaEmail.receiveEmail();

      for (JavaMessage message : messages) {
        try {
          System.out.println("Message : " + message.getContent());
          String[] subject = message.getSubject().split(" ");
          int journeyId = Integer.parseInt(subject[subject.length - 1]);
          int stage = store.getJourneyStage(journeyId).toCode();

          switch (stage) {
            case 0:
              String quote = message.getContent().split(" ")[0];
              store.updateQuote(journeyId, quote.split("-")[0], quote.split("-")[1]);
              store.updateStage(journeyId, JourneyStage.QuoteReceived);
              break;
            case 2:
              store.offerAppointment(journeyId, message.getContent().split(" ")[0]);
              store.updateStage(journeyId, JourneyStage.AppointmentOfferReceived);
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
        Thread.sleep(1000 * 60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
