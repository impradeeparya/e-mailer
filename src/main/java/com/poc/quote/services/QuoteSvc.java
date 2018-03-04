package com.poc.quote.services;

import static com.poc.quote.constants.Constants.*;

import com.itextpdf.text.DocumentException;
import com.poc.quote.dto.QuoteInfo;
import com.poc.quote.enums.FileType;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class QuoteSvc {


  @Autowired
  private FileGenerator fileGenerator;

  @Autowired
  private Mailer mailer;

  @Autowired
  private Environment environment;

  public void sendFile(FileType fileType, QuoteInfo quoteInfo)
      throws DocumentException, MessagingException {
    System.out.println(fileType + " " + quoteInfo);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    fileGenerator.writeToOutputStream(outputStream, quoteInfo.toMap());
    Map<String, Object> mailData = new HashMap<>();
    mailData.put(TO, environment.getProperty("to"));
    mailData.put(FROM, environment.getProperty("from"));
    mailData.put(ATTACHMENT, outputStream.toByteArray());
    mailData.put(ATTACHMENT_TYPE, "application/pdf");
    mailData.put(ATTACHMENT_NAME, "quote_info.pdf");
    mailData.put(SUBJECT, "QUOTE REQUIRED");
    mailer.sendMultipart(mailData);
  }
}
