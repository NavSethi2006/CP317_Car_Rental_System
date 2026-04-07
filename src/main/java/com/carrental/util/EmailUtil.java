package main.java.com.carrental.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    private static final String FROM_EMAIL = "drivenownoreply@gmail.com";
    private static final String FROM_PASSWORD = "hxoyyskercorrpnw"; // no spaces
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;

    /**
     * Sends an email asynchronously so it doesn't block the main thread.
     */
    public static void sendEmail(String recipientEmail, String subject, String body) {
        new Thread(() -> {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.ssl.trust", SMTP_HOST);

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM_EMAIL));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                message.setSubject(subject);
                message.setText(body);
                Transport.send(message);
                System.out.println("Email sent successfully to " + recipientEmail);
            } catch (MessagingException e) {
                System.err.println("Failed to send email to " + recipientEmail + ": " + e.getMessage());
                // Do not throw – registration should not fail because of email issues
            }
        }).start();
    }
}