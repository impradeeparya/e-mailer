package com.poc.fileoperation.services;

import com.itextpdf.text.DocumentException;
import static com.poc.fileoperation.Constants.*;
import com.poc.fileoperation.dto.UserInfo;
import com.poc.fileoperation.enums.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileSvc {


  @Autowired
  private FileGenerator fileGenerator;

  @Autowired
  private Mailer mailer;

  @Autowired
  private Environment environment;

  public void sendFile(FileType fileType, UserInfo userInfo)
      throws DocumentException, MessagingException {
    System.out.println(fileType + " " + userInfo);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    fileGenerator.writeToOutputStream(outputStream, userInfo.toMap());
    Map<String, Object> mailData = new HashMap<>();
    mailData.put(TO, environment.getProperty("to"));
    mailData.put(FROM, environment.getProperty("from"));
    mailData.put(ATTACHMENT, outputStream.toByteArray());
    mailData.put(ATTACHMENT_TYPE, "application/pdf");
    mailData.put(ATTACHMENT_NAME, "user_info.pdf");
    mailData.put(SUBJECT, "This is subject");
    mailer.sendMultipart(mailData);
  }
}
