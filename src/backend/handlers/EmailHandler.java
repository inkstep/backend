package handlers;


import email.JavaEmail;
import email.JavaMessage;
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
          int status = store.getJourneyStatus(journeyId);

          switch (status) {
            case 0:
              store.updateQuote(journeyId, message.getContent().split(" ")[0]);
              store.updateStatus(journeyId, 1);
              break;
            case 1:
              store.offerAppointment(journeyId, message.getContent().split(" ")[0]);
              store.updateStatus(journeyId, 2);
              break;
            default:
              System.out.println("Status not implemented");
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