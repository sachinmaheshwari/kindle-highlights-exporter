package com.sm.kindlehighlightsexporter;

import static java.lang.System.out;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Option;

@Component
@CommandLine.Command(name = "Highlight parser command", mixinStandardHelpOptions = true, version = "1.0.0", description = "This command will parse the highlights from the given file and then spit out the same in the format given in the format file.")
public class ParseCommand implements Callable<Integer> {

  @Option(names = { "-s", "--source"}, paramLabel = "./My Clippings.txt", description = "The complete path to the highlight file (My Clippings.txt).")
  private File highlights;

  @Option(names = { "-f", "--format"}, paramLabel = "./format.txt",description = "The complete path to the format file..")
  private File format;

  @Override
  public Integer call() throws Exception {
    String source = new String(
        Files.readAllBytes(Paths.get(highlights.toURI())));

    String formatPattern = new String(
        Files.readAllBytes(Paths.get(format.toURI())));
    parseData(source,formatPattern);
    return 0;
  }

  public void parseData(String source, String formatPattern) {
    String[] notes = source.split("==========");

    Parser parser = new Parser();


    Formatter formatter = new Formatter(formatPattern);

    Map<String, List<Note>> notesCollection = new HashMap<>();

    for(String note : notes) {
      if(note.trim().isEmpty()) {
        continue;
      }

      Note parsedNote = parser.parse(note.trim());

      if(notesCollection.containsKey(parsedNote.getBookName())){
        notesCollection.get(parsedNote.getBookName()).add(parsedNote);
      } else {
        LinkedList<Note> list = new LinkedList<>();
        list.add(parsedNote);
        notesCollection.put(parsedNote.getBookName(), list);
      }
    }

    for(Entry<String, List<Note>> entry : notesCollection.entrySet()){
      out.println("Book: " + entry.getKey());
      out.println("=========");
      for (Note note : entry.getValue()){
        out.println(formatter.format(note));
      }

      out.println("");
    }

  }

}
