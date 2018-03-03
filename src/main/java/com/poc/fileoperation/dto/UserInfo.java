package com.poc.fileoperation.dto;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {

  private String name;
  private String emailId;
  private String message;

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

  @Override
  public String toString() {
    return "UserInfo{" + "name='" + name + '\'' + ", emailId='" + emailId + '\'' + ", message='"
        + message + '\'' + '}';
  }

  public Map<String, String> toMap() {
    Map<String, String> userData = new HashMap<>();
    userData.put("name", this.name);
    userData.put("emailId", this.emailId);
    userData.put("message", this.message);
    return userData;
  }
}
