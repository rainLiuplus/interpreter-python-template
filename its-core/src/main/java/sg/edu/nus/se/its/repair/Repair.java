package sg.edu.nus.se.its.repair;

import java.util.List;
import sg.edu.nus.se.its.interpreter.Interpreter;
import sg.edu.nus.se.its.model.ErrorLocalisation;
import sg.edu.nus.se.its.model.Input;
import sg.edu.nus.se.its.model.Program;
import sg.edu.nus.se.its.model.RepairCandidate;

/**
 * Interface for the repair module.
 */
public interface Repair {

  /**
   * Repairs the student's incorrect program, given the reference solution and a mapping of
   * erroneous blocks. The repaired program is in our internal representation format.
   *
   * @param referenceProgram the parsed internal representation of the reference program.
   * @param wrongProgram the parsed internal representation of the student's incorrect program.
   * @param errorLocations a list of bijective set of erroneous locations
   * @param inputs set of inputs used for the program
   * @return a list of possible repairs for the student's incorrect program
   * @see Program
   */
  List<RepairCandidate> repair(Program referenceProgram, Program wrongProgram,
      ErrorLocalisation errorLocations, List<Input> inputs, Interpreter interpreter)
      throws Exception;
}
