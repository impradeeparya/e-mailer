package com.poc.fileoperation.controllers;

import com.itextpdf.text.DocumentException;
import com.poc.fileoperation.dto.UserInfo;
import com.poc.fileoperation.enums.FileType;
import com.poc.fileoperation.services.FileSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/file")
public class FileController {

  @Autowired
  private FileSvc fileSvc;

  @PostMapping(value = "/send/{type}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createFile(@PathVariable("type") FileType fileType,
      @RequestBody UserInfo userInfo) throws DocumentException, MessagingException {
    ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
    fileSvc.sendFile(fileType, userInfo);
    return responseEntity;
  }
}
