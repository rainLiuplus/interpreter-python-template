package sg.edu.nus.se.its.model;

/**
 * Data class representing the input to a program. It consists of function arguments and I/O inputs.
 */
public class Input {

  /**
   * I/O input to the Program.
   */
  private String[] inputs;

  /**
   * function arguments.
   */
  private String[] args;

  public Input(String[] inputs, String[] args) {
    this.inputs = inputs == null ? new String[0]: inputs;
    this.args = args == null ? new String[0]: args;
  }

  public Input() {
    this.inputs = new String[0];
    this.args = new String[0];
  }

  public String[] getInputs() {
    return inputs;
  }

  public String[] getArgs() {
    return args;
  }

}
