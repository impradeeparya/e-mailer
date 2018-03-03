package com.poc.fileoperation.services;

import static com.poc.fileoperation.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

@Service
public class Mailer {

  @Autowired
  private Environment environment;

  public void sendMultipart(Map<String, Object> data) throws MessagingException {

    Properties properties = fetchMailProperties();

    Session session = getSession(properties);

    DataSource dataSource =
        new ByteArrayDataSource((byte[]) data.get(ATTACHMENT), (String) data.get(ATTACHMENT_TYPE));
    MimeBodyPart mimeBodyPart = new MimeBodyPart();
    mimeBodyPart.setDataHandler(new DataHandler(dataSource));
    mimeBodyPart.setFileName((String) data.get(ATTACHMENT_NAME));

    MimeMultipart mimeMultipart = new MimeMultipart();
    mimeMultipart.addBodyPart(mimeBodyPart);

    // create the sender/recipient addresses
    InternetAddress iaSender = new InternetAddress(environment.getProperty(FROM));
    InternetAddress iaRecipient = new InternetAddress(environment.getProperty(TO));

    MimeMessage mimeMessage = new MimeMessage(session);
    mimeMessage.setSender(iaSender);
    mimeMessage.setSubject((String) data.get(SUBJECT));
    mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
    mimeMessage.setContent(mimeMultipart);

    Transport.send(mimeMessage);
  }

  private Session getSession(Properties properties) {
    return Session.getInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(environment.getProperty("from"),
            environment.getProperty("user.password"));
      }
    });
  }

  private Properties fetchMailProperties() {
    Properties properties = new Properties();
    properties.put("mail.smtp.host", environment.getProperty("mail.smtp.host"));
    properties.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));
    properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
    properties.put("mail.smtp.starttls.enable",
        environment.getProperty("mail.smtp.starttls.enable"));
    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    return properties;
  }
}
