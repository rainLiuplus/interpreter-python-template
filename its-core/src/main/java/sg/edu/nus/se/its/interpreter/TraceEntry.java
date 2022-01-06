package sg.edu.nus.se.its.interpreter;

import sg.edu.nus.se.its.model.Memory;

/**
 * Entry to a trace object.
 */
public class TraceEntry {

  private String functionName;

  /**
   * Control flow block ID.
   */
  private int location;

  private Memory mem;

  /**
   * Creates a new trace entry for the given function name, memory, and code location.
   */
  public TraceEntry(String functionName, int loc, Memory mem) {
    this.functionName = functionName;
    this.location = loc;
    this.mem = mem;
  }

  public String getFunctionName() {
    return functionName;
  }

  /**
   * Get the ID of the first control-flow block in the trace.
   */
  public int getLocation() {
    return location;
  }

  public Memory getMem() {
    return mem;
  }

  @Override
  public String toString() {
    return String.format("(fnc=%s, loc=%d, mem=%s)", this.functionName, this.location, this.mem);
  }
}
