package com.brahalla.Cerberus.utils.email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * Created by kfirf on 9/16/16.
 */
public class SendEmailUtil implements SendEmail {

    private final String emailFrom;
    private final char[] emailFromPassword;
    private final Properties properties;

    public SendEmailUtil(String emailFrom, char[] emailFromPassword) {
        this.emailFrom = emailFrom;
        this.emailFromPassword = emailFromPassword;
        this.properties = getProperties();
    }

    @Override
    public void sendRegularEmail(String emailTo, String subject, String content) throws MessagingException {
        Session session = getSession();
        Message message = getMessage(emailTo, subject, session);
        message.setText(content);
        Transport.send(message);
    }

    @Override
    public void sendHtmlEmail(String emailTo, String subject, String content) throws MessagingException {
        Session session = getSession();
        Message message = getMessage(emailTo, subject, session);
        // For HTML
        message.setContent(content, "text/html");
        Transport.send(message);
    }

    @Override
    public void sendWithFile(String emailTo, String subject, String content, String filePath) throws MessagingException {
        Session session = getSession();
        Message message = getMessage(emailTo, subject, session);
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        // Fill the message
        messageBodyPart.setText(content);
        // Create a multipart message
        Multipart multipart = new MimeMultipart();
        // Set text message part
        multipart.addBodyPart(messageBodyPart);
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filePath);
        multipart.addBodyPart(messageBodyPart);
        // Send the complete message parts
        message.setContent(multipart);
        Transport.send(message);
    }

    private Message getMessage(String emailTo, String subject, Session session) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailFrom));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        return msg;
    }

    private Session getSession() {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, String.valueOf(emailFromPassword));
            }
        });
        return session;
    }

    private Properties getProperties() {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        return properties;
    }

}
