package com.sm.kindlehighlightsexporter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Note {

  private String bookName;
  private String author;
  private String text;
  private String createdOn;
  private String page;
  private String location;
  private String type;

  public String getBookName() {
    return bookName;
  }

  public String getAuthor() {
    return author;
  }

  public String getText() {
    return text;
  }

  public String getCreatedOn() {
    return createdOn;
  }

  public String getPage() {
    return page;
  }

  public String getLocation() {
    return location;
  }

  public String getType() {
    return type;
  }
}
