package sg.edu.nus.se.its.feedback;

import java.util.List;
import sg.edu.nus.se.its.model.Program;
import sg.edu.nus.se.its.model.RepairCandidate;

/**
 * Interface for the feedback module.
 */
public interface Feedback {

  /**
   * Generates feedback for the student based on the identified list of repairs.
   *
   * @param repairCandidates -- list of repair candidates
   * @param submittedProgram -- the submitted program
   * @return feedback in form of a String object
   */
  public String provideFeedback(List<RepairCandidate> repairCandidates, Program submittedProgram);

}
