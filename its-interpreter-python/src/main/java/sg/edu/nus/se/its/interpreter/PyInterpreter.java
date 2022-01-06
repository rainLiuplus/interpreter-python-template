package sg.edu.nus.se.its.interpreter;

import org.apache.commons.lang3.NotImplementedException;
import sg.edu.nus.se.its.model.Constant;
import sg.edu.nus.se.its.model.Function;
import sg.edu.nus.se.its.model.Input;
import sg.edu.nus.se.its.model.Memory;
import sg.edu.nus.se.its.model.Operation;
import sg.edu.nus.se.its.model.Program;
import sg.edu.nus.se.its.model.Variable;

/**
 * Concrete implementation of the interpreter for Python.
 * TODO not implemented yet.
 */
public class PyInterpreter implements Interpreter {

  public PyInterpreter(int timeout, String entryfnc) {
    throw new NotImplementedException();
  }

  public Trace executeProgram(Program program) {
    throw new NotImplementedException();
  }

  public Trace executeProgram(Program program, Input input) {
    throw new NotImplementedException();
  }

  public Object execute(Executable executable, Memory memory) {
    throw new NotImplementedException();
  }

  public Object executeFunction(Function function, Memory memory) {
    throw new NotImplementedException();
  }

  public Object executeConstant(Constant constant, Memory memory) {
    throw new NotImplementedException();
  }

  public Object executeOperation(Operation operation, Memory memory) {
    throw new NotImplementedException();
  }

  public Object executeVariable(Variable variable, Memory memory) {
    throw new NotImplementedException();
  }

  public void setTimeout(int timeout) {
    throw new NotImplementedException();
  }

}
