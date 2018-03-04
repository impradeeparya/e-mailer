package com.poc.quote.dto;

import java.util.HashMap;
import java.util.Map;

public class QuoteInfo {

  private String name;
  private String emailId;
  private String message;
  private String mobile;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Override
  public String toString() {
    return "QuoteInfo{" + "name='" + name + '\'' + ", emailId='" + emailId + '\'' + ", message='"
        + message + '\'' + ", mobile='" + mobile + '\'' + '}';
  }

  public Map<String, String> toMap() {
    Map<String, String> quoteInfo = new HashMap<>();
    quoteInfo.put("name", this.name);
    quoteInfo.put("emailId", this.emailId);
    quoteInfo.put("message", this.message);
    quoteInfo.put("mobile", this.mobile);
    return quoteInfo;
  }
}
