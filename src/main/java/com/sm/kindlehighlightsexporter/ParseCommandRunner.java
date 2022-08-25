package com.sm.kindlehighlightsexporter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IFactory;

@Component
public class ParseCommandRunner implements CommandLineRunner, ExitCodeGenerator {

  // auto-configured to inject PicocliSpringFactory
  private final IFactory factory;

  private final ParseCommand myCommand;

  private int exitCode;

  public ParseCommandRunner(IFactory factory, ParseCommand myCommand) {
    this.factory = factory;
    this.myCommand = myCommand;
  }

  @Override
  public void run(String... args) throws Exception {
    exitCode = new CommandLine(myCommand, factory).execute(args);
  }

  @Override
  public int getExitCode() {
    return exitCode;
  }
}
