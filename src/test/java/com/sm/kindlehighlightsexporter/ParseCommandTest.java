package com.sm.kindlehighlightsexporter;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import sun.misc.IOUtils;


public class ParseCommandTest {

  @Test
  public void testParseCommand() throws IOException {
    ParseCommand parseCommand = new ParseCommand();

    String source = readFile("My Clippings.txt");
    String formatPattern = readFile("format.txt");

    parseCommand.parseData(source, formatPattern);

  }

  private String readFile(String resourceName) throws IOException {
    InputStream is = ClassLoader.getSystemResourceAsStream(resourceName);
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    int nRead;
    byte[] data = new byte[16384];

    while ((nRead = is.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }

    return new String(buffer.toByteArray());
  }

}