package com.sm.kindlehighlightsexporter;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormatterTest {

  private Formatter formatter;

  @BeforeMethod
  public void setUp() {
    formatter = new Formatter("{page},{type} - {text}");
  }

  @Test
  public void testFormat() {
    String sampleHighlight = "Why We Sleep: Unlocking the Power of Sleep and Dreams (Matthew Walker)\n"
        + "- Your Highlight on page 9 | Location 135-135 | Added on Sunday, December 19, 2021 10:56:55 PM\n"
        + "\n"
        + "humanity with the sleep it so desperately";
    Note note = new Parser().parse(sampleHighlight);
    String actual = formatter.format(note);
    assertThat(actual).isEqualTo("9,Highlight - humanity with the sleep it so desperately");
  }
}
