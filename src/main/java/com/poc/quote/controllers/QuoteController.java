package com.poc.quote.controllers;

import com.itextpdf.text.DocumentException;
import com.poc.quote.dto.QuoteInfo;
import com.poc.quote.enums.FileType;
import com.poc.quote.services.QuoteSvc;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quote")
public class QuoteController {

  @Autowired
  private QuoteSvc quoteSvc;

  @PostMapping(value = "/send/{type}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity postQuote(@PathVariable("type") FileType fileType,
      @RequestBody QuoteInfo quoteInfo) throws DocumentException, MessagingException {
    ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
    quoteSvc.sendFile(fileType, quoteInfo);
    return responseEntity;
  }
}
