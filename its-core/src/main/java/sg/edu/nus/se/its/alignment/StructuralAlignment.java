package sg.edu.nus.se.its.alignment;

import sg.edu.nus.se.its.exception.AlignmentException;
import sg.edu.nus.se.its.model.Program;


/**
 * Interface for the syntactical alignment of the reference program and the submitted program.
 */
public interface StructuralAlignment {

  /**
   * Generates the structural alignment for the given two programs.
   *
   * @param reference - the reference program
   * @param submission - the submission program
   * @return the structural mapping
   * @throws AlignmentException - the alignment exception
   */
  StructuralMapping generateStructuralAlignment(Program reference, Program submission)
      throws AlignmentException;
}
