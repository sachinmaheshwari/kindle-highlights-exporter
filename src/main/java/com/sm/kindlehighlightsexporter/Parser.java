package com.sm.kindlehighlightsexporter;

public class Parser {

  public Note parse(String highlight) {
    String[] data = highlight.split(System.lineSeparator());
    String bookName = data[0].split("[(]")[0].trim();
    String author=data[0].split("[(]")[1].trim().replace(")","");
    String text = data[3];
    String[] metadata = data[1].split("[|]");
    String createdOn= metadata[2].replace("Added on", "").trim();
    String page=metadata[0].replace("-", "").replace("Your Highlight on page", "").replace("Your Note on page", "").trim();
    String location=metadata[1].replace("Location","").trim();
    String type = metadata[0].split(" ")[2];
    return new Note(bookName, author, text, createdOn, page, location, type);
  }
}
