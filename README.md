# its-interpreter-python-template (Zhiyu)

## Overview
As a prerequisite of error localization and program repair, an interpreter is used to concretely execute a program to get its execution trace with specific input. At this stage, the project assumes that the program is presented in some CFG-based representation. In this project, the students are asked to implement an interpreter for the CFG-based representation. Given a program *P* in CFG-based representation and an input *i* for *P*, the interpreter should generate the execution trace which records all variables' values during interpretation. In general, the interpreter mainly requires the following two steps:

* Implement highly reusable and flexible code structure that traverse the CFG-representation of program *P* correctly.
* Implement functions to execute all components in *P*'s CFG representation and record all variables' values in Memory with input *i* step by step during the traversing.

Furthermore, the students are asked to use their interpreter to implement a simple variant of a trace-based error localization. It should use the resulting execution traces from the interpreter to detect mismatches between the reference and submitted program with regard to the values along the execution traces.

## Entry Points
*  [sg.edu.nus.se.its.interpreter.PyInterpreter](./its-interpreter-c/src/main/java/sg/edu/nus/se/its/interpreter/PyInterpreter.java)
```
/**
 * Concrete implementation of the interpreter for Python.
 */
public class PyInterpreter implements Interpreter {

  public PyInterpreter(int timeout, String entryfnc) {
    throw new NotImplementedException();
  }

  public Trace executeProgram(Program program) {
    throw new NotImplementedException();
  }
  ...
}
```


* You can use `mvn clean compile test` to build and test your implementation.

## Restrictions
* You are not allowed to change any code in the [sg.edu.nus.its.its-core](./its-core).
* You need to stick to the provided interfaces.
* You are not allowed to change the file/class name or move [sg.edu.nus.se.its.interpreter.PyInterpreter](./its-interpreter-c/src/main/java/sg/edu/nus/se/its/interpreter/PyInterpreter.java).
* If you would require any other dependencies or libraries, you first need to seek approval by the tutors.
* You are not allowed to change any file within [.github](./.github).

## Todos (Zhiyu)

- [ ] Test Ressources (json)
- [ ] Refactor advanced test
- [ ] Add basic test

