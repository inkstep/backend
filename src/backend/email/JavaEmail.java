package email;

import com.sun.mail.pop3.POP3Store;
import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
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

  private String smtpPort = "587";
  private String popPort = "995";
  private Properties emailSmtpProp;
  private Properties emailPopProp;
  private Session mailSession;
  private MimeMessage emailMessage;
  private String emailStmpHost = "smtp.gmail.com";
  private String emailAccount = "inksteptattoo";
  private String emailPassword = System.getenv("email_password");

  private void setMailServerProperties() {
    emailSmtpProp = System.getProperties();
    emailSmtpProp.put("mail.smtp.port", smtpPort);
    emailSmtpProp.put("mail.smtp.auth", "true");
    emailSmtpProp.put("mail.smtp.starttls.enable", "true");

    emailPopProp = System.getProperties();
    emailPopProp.put("mail.pop3s.host", "pop.gmail.com");
    emailPopProp.put("mail.pop3s.port", popPort);
    emailPopProp.put("mail.pop3s.auth", "true");
    emailPopProp.put("mail.pop3s.starttls.enable", "true");
  }

  private void createEmailMessage(String to, String message, String subject, String toReply,
    List<File> files) throws MessagingException {
    String[] toEmails = {to};

    mailSession = Session.getDefaultInstance(emailSmtpProp, null);
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

    Transport transport = mailSession.getTransport("smtp");

    transport.connect(emailStmpHost, emailAccount, emailPassword);
    transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
    transport.close();
    System.out.println("email sent successfully.");
  }

  public void receiveEmail() {
    try {
      setMailServerProperties();
      Session emailSession = Session.getDefaultInstance(emailPopProp);
      POP3Store emailStore = (POP3Store) emailSession.getStore("pop3s");
      emailStore.connect("inksteptattoo@gmail.com", emailPassword);

      Folder emailFolder = emailStore.getFolder("INBOX");
      emailFolder.open(Folder.READ_WRITE);

      //4) retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      for (int i = 0; i < messages.length; i++) {
        Message message = messages[i];
        message.setFlag(Flag.DELETED, true);
      }

      //5) close the store and folder objects
      emailFolder.close(true);
      emailStore.close();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
