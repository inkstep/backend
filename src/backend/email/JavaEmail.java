package email;

import java.io.File;
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
import java.util.List;
import java.util.Properties;
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

  private void createEmailMessage(String to, String message, String subject, String toReply,
    List<File> files) throws MessagingException {
    String[] toEmails = {to};

    mailSession = Session.getDefaultInstance(emailProperties, null);
    emailMessage = new MimeMessage(mailSession);

    for (String toEmail : toEmails) {
      emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    }

    Address[] replyAddresses = {new InternetAddress(toReply)};
    emailMessage.setReplyTo(replyAddresses);
    emailMessage.setSubject(subject);

    // Set the body
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText(message);
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    int imgCount = 0;
    for (File file : files) {
      // Add attachtment
      messageBodyPart = new MimeBodyPart();
      DataSource source = new FileDataSource(file);
      messageBodyPart.setDataHandler(new DataHandler(source));
      messageBodyPart.setFileName("refImg" + imgCount + ".png");
      multipart.addBodyPart(messageBodyPart);
    }

    // Send the complete message parts
    emailMessage.setContent(multipart);
  }

  public void sendEmail(String to, String message, String subject, String toReply,
    List<File> files) throws MessagingException {
    setMailServerProperties();
    createEmailMessage(to, message, subject, toReply, files);

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
