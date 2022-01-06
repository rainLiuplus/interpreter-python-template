package sg.edu.nus.se.its.alignment;

import sg.edu.nus.se.its.exception.AlignmentException;
import sg.edu.nus.se.its.model.Program;

/**
 * The interface Variable alignment.
 */
public interface VariableAlignment {

  /**
   * Generates variable alignment.
   *
   * @param reference - the reference program
   * @param submission - the submission program
   * @param strucAlignment - the structural alignment
   * @return the variable alignment
   * @throws AlignmentException - the alignment exception
   */
  VariableMapping generateVariableAlignment(Program reference, Program submission,
      StructuralMapping strucAlignment) throws AlignmentException;
}
