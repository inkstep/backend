package handlers;


import email.JavaEmail;
import email.JavaMessage;

public class EmailHandler implements Runnable {
  private JavaEmail javaEmail = new JavaEmail();

  @Override
  public void run() {
    while (true) {
      JavaMessage[] messages = javaEmail.receiveEmail();

      for (JavaMessage message : messages) {
        try {
          System.out.println("Message : " + message.getContent());
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