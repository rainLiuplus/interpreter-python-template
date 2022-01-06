package sg.edu.nus.se.its.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static sg.edu.nus.se.its.util.TestUtils.loadProgramByName;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.se.its.model.Input;
import sg.edu.nus.se.its.model.Memory;
import sg.edu.nus.se.its.model.Program;
import sg.edu.nus.se.its.model.Variable;
import sg.edu.nus.its.parser.ParserWrapper;
import sg.edu.nus.se.its.util.Constants;
import sg.edu.nus.se.its.util.TestUtils;
import sg.edu.nus.se.its.util.UtilFunctions;

/**
 * Simple test collection for the interpreter.
 */
public class BasicTest {

  private static Interpreter PyInterpreter;
  private static ParserWrapper parser;

  @BeforeEach
  public void initializeInterpreter() {
    PyInterpreter = new PyInterpreter(50000, Constants.DEFAULT_ENTRY_FUNCTION_NAME);
  }

  @BeforeEach
  public void initializeParser() {
    parser = new ParserWrapper();
  }

  @Test
  public void duplicateEliminationTest() {
    String task = "DuplicateElimination";
    List<String> inputs = TestUtils.loadInputsByPythonTaskName(task);
    List<Input> inputsForInterpreter = TestUtils.loadInputsForInterpreterByPythonTaskName(task);
    File[] programs = TestUtils.loadProgramsByPythonTaskName(task);
    assert inputs != null;
    assert inputsForInterpreter != null;
    assert programs != null;

    PyInterpreter = new PyInterpreter(50000, "remove_extras");


    StringBuilder progInputs = new StringBuilder();
    for (String input : inputs) {
      progInputs.append(String.format("print(%s)\n", input));
    }
    int pass = 0;
    for (File progBody : programs) {
      try {
        String expectedOutput = UtilFunctions.executeProgramForPython(progBody, progInputs.toString());
        Program program = parser.parse(progBody);
        String actualOutput = "";
        for (Input input : inputsForInterpreter) {
          actualOutput += PyInterpreter.executeProgram(program, input).getLastEntry().getMem()
                  .get("$ret'").toString();
        }
        if (!"[1, 2, 3][1, 5, 3, 2][][3, 4, 5, 1][3, 4, 5, 1][3, 4, 5, 1]".equals(actualOutput)) {
          System.out.printf("Program %s fails\n", progBody.getName());
        } else {
          pass++;
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (RuntimeException e) {
        System.out.printf("Program %s fails\n", progBody.getName());
      }
    }
    System.out.printf("pass rate = %d/%d(%f)", pass, 546, (float) pass / 546);
  }

}
