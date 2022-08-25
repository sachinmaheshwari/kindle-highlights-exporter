package com.sm.kindlehighlightsexporter;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParserTest {

  private Parser parser;

  @BeforeMethod
  public void setUp() {
    parser = new Parser();
  }

  @Test
  public void testParsingHighlight() {
    String sampleHighlight = "Why We Sleep: Unlocking the Power of Sleep and Dreams (Matthew Walker)\n"
        + "- Your Highlight on page 9 | Location 135-135 | Added on Sunday, December 19, 2021 10:56:55 PM\n"
        + "\n"
        + "humanity with the sleep it so desperately";
    Note note = parser.parse(sampleHighlight);
    assertThat(note).isNotNull();
    assertThat(note.getBookName()).isEqualTo("Why We Sleep: Unlocking the Power of Sleep and Dreams");
    assertThat(note.getAuthor()).isEqualTo("Matthew Walker");
    assertThat(note.getText()).isEqualTo("humanity with the sleep it so desperately");
    assertThat(note.getCreatedOn()).isEqualTo("Sunday, December 19, 2021 10:56:55 PM");
    assertThat(note.getPage()).isEqualTo("9");
    assertThat(note.getLocation()).isEqualTo("135-135");
    assertThat(note.getType()).isEqualTo("Highlight");
  }

  @Test
  public void testToString() {
    String sampleHighlight = "Practices of an Agile Developer (Andy Hunt; Venkat Subramaniam)\n"
        + "- Your Highlight on page 29 | Location 436-437 | Added on Wednesday, August 24, 2022 7:22:29 PM\n"
        + "\n"
        + "Sometimes the best plans fail in the absence of courage. Despite the dangers—the real and metaphorical torpedoes—you need to charge ahead and do what’s right.";
    Note note = parser.parse(sampleHighlight);
    assertThat(note.toString()).isEqualTo("Note(bookName=Practices of an Agile Developer, author=Andy Hunt; Venkat Subramaniam, text=Sometimes the best plans fail in the absence of courage. Despite the dangers—the real and metaphorical torpedoes—you need to charge ahead and do what’s right., createdOn=Wednesday, August 24, 2022 7:22:29 PM, page=29, location=436-437, type=Highlight)");
  }

  @Test
  public void testNoteParsing() {
    String sampleNote = "Practices of an Agile Developer (Andy Hunt; Venkat Subramaniam)\n"
        + "- Your Note on page 29 | Location 437 | Added on Wednesday, August 24, 2022 7:22:29 PM\n"
        + "\n"
        + "This is a note.";
    Note note = parser.parse(sampleNote);
    assertThat(note.getType()).isEqualTo("Note");
    assertThat(note.getBookName()).isEqualTo("Practices of an Agile Developer");
  }

}
