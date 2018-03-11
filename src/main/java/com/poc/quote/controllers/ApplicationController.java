package com.poc.quote.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e-mail")
public class ApplicationController {

  @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity postQuote() {
    return new ResponseEntity("{\"message\":\"hello from server\"}", HttpStatus.OK);
  }
}
