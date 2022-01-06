package sg.edu.nus.its.parse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import sg.edu.nus.its.parser.ParserWrapper;
import sg.edu.nus.se.its.model.Program;

/**
 * Simple test collection for the parser wrapper using Python.
 */
public class ParserTest {

  @Test

  void basicTestForC() {
    ParserWrapper parser = new ParserWrapper();

    String workingDir = System.getProperty("user.dir");
    File sourceFile = new File(workingDir + "/src/test/resources/test.c");

    Program program = null;
    try {
      program = parser.parse(sourceFile);
    } catch (IOException e) {
      e.printStackTrace();
      fail("No exception should happen.");
    }

    System.out.println(program);

    assertNotNull(program);
  }

  @Test
  void basicTestForPython() {
    ParserWrapper parser = new ParserWrapper();

    String workingDir = System.getProperty("user.dir");
    File sourceFile = new File(workingDir + "/src/test/resources/test.py");

    Program program = null;
    try {
      program = parser.parse(sourceFile);
    } catch (IOException e) {
      e.printStackTrace();
      fail("No exception should happen.");
    }

    System.out.println(program);

    assertNotNull(program);
  }

}
