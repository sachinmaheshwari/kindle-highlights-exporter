package com.sm.kindlehighlightsexporter;



public class Formatter {


  public static final String PAGE = "{page}";
  public static final String TEXT = "{text}";
  public static final String TYPE = "{type}";
  public static final String AUTHOR = "{author}";
  public static final String CREATED_ON = "{createdOn}";
  private final String format;

  public Formatter(String format) {
    this.format = format;
  }

  public String format(Note note){
    return format
        .replace(PAGE, note.getPage())
        .replace(TEXT, note.getText())
        .replace(TYPE,note.getType())
        .replace(CREATED_ON, note.getCreatedOn())
        .replace(AUTHOR, note.getAuthor());
  }

}
