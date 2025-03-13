package Tema4.mail;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;



public class EmailManager {

    private Properties properties;
    private Session session;

    private void setSMTPServerProperties() {
        properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", "true");
        session = Session.getInstance(properties, null);
    }

    private Transport connectSMTPServer(String emailAddress, String password) throws NoSuchProviderException, MessagingException {
        Transport t = (Transport) session.getTransport("smtp");
        t.connect(properties.getProperty("mail.smtp.host"), emailAddress,
                password);
        return t;
    }

    private Message createMessageCore(String from, String to, String subject) throws AddressException, MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        return message;
    }

    private Message createTextMessage(String from, String to, String subject,
                                      String messageText) throws MessagingException, AddressException, IOException {
        Message message = createMessageCore(from, to, subject);
        message.setText(messageText);
        return message;
    }

    public void sendTextMessage(String from, String to, String subject,
                                String messageText, String user, String password) throws AddressException, MessagingException, IOException {
        setSMTPServerProperties();
        Message message = createTextMessage(from, to, subject, messageText);
        Transport t = connectSMTPServer(user, password);
        t.sendMessage(message, message.getAllRecipients());
        t.close();
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce email to:");
            String emailTo = sc.nextLine();
            System.out.print("Introduce email from:");
            String emailFrom = sc.nextLine();
            System.out.print("Introduce password:");
            String passwordFrom = sc.nextLine();
            sc.close();
            EmailManager emailManager = new EmailManager();
            emailManager.sendTextMessage(emailFrom, emailTo,
                    "Sample text email without attachment",
                    "Test Message from Java.",
                    emailFrom, passwordFrom);
            System.out.println("Email sent.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}