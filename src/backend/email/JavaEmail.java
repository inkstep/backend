package email;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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

  private void createEmailMessage(String to, String message, String subject,
      String toReply, List<String> filenames)
      throws MessagingException {
    String[] toEmails = { to };

    mailSession = Session.getDefaultInstance(emailProperties, null);
    emailMessage = new MimeMessage(mailSession);

    for (int i = 0; i < toEmails.length; i++) {
      emailMessage.addRecipient(Message.RecipientType.TO,
          new InternetAddress(toEmails[i]));
    }

    Address[] replyAddresses = { new InternetAddress(toReply)};
    emailMessage.setReplyTo(replyAddresses);

    emailMessage.setSubject(subject);
    emailMessage.setText(message);
  }

  public void sendEmail(String to, String message, String subject,
      String toReply, List<String> filenames) throws MessagingException {
    setMailServerProperties();
    createEmailMessage(to, message, subject, toReply, filenames);

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