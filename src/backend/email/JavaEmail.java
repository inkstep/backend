package email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import java.util.stream.Stream;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaEmail {

  private Properties emailProperties;
  private Session mailSession;
  private MimeMessage emailMessage;

  private void setMailServerProperties() {

    String emailPort = "587";

    emailProperties = System.getProperties();
    emailProperties.put("mail.smtp.port", emailPort);
    emailProperties.put("mail.smtp.auth", "true");
    emailProperties.put("mail.smtp.starttls.enable", "true");
  }

  private void createEmailMessage(String to, String message, String subject)
      throws MessagingException {
    String[] toEmails = { to };
    String emailBody = "This is an email sent by JavaMail api.";

    mailSession = Session.getDefaultInstance(emailProperties, null);
    emailMessage = new MimeMessage(mailSession);

    for (int i = 0; i < toEmails.length; i++) {
      emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
    }

    emailMessage.setSubject(subject);
    emailMessage.setContent(emailBody, "text/html");
    emailMessage.setText(message);
  }

  public void sendEmail(String to, String message, String subject)
      throws MessagingException {
    setMailServerProperties();
    createEmailMessage(to, message, subject);

    String emailHost = "smtp.gmail.com";
    String fromUser = "Inksteptattoo";

    String fromUserEmailPassword = System.getenv("email_password");

    Transport transport = mailSession.getTransport("smtp");

    transport.connect(emailHost, fromUser, fromUserEmailPassword);
    transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
    transport.close();
    System.out.println("email sent successfully.");
  }

}