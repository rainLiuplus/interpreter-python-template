package sg.edu.nus.se.its.errorlocalizer;

import org.apache.commons.lang3.NotImplementedException;
import sg.edu.nus.se.its.alignment.StructuralMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import sg.edu.nus.se.its.alignment.VariableMapping;
import sg.edu.nus.se.its.interpreter.Interpreter;
import sg.edu.nus.se.its.interpreter.Trace;
import sg.edu.nus.se.its.interpreter.TraceEntry;
import sg.edu.nus.se.its.model.Input;
import sg.edu.nus.se.its.model.Memory;
import sg.edu.nus.se.its.model.Program;
import sg.edu.nus.se.its.model.Variable;
import sg.edu.nus.se.its.util.UtilFunctions;


/**
 * Implements the concrete error localizer based on CFG alignment.
 */
public class BasicErrorLocalizer implements ErrorLocalizer {

    /**
     * Returns a list of erroneous blocks of the submitted program, given the reference program, the
     * student program and the mapping of aligned blocks.
     *
     * @param submittedProgram - the parsed internal representation of the student's program.
     * @param referenceProgram - the parsed internal representation of the reference program. <<<<<<<
     *        HEAD
     * @param inputs - list of String value for the inputs to test for (can be null) ======= >>>>>>>
     *        master
     * @param functionName - function to analyze
     * @param structuralMapping - the alignment of the reference program with the student's program.
     * @param variableMapping - the mapping of the variables in both programs
     * @param interpreter - interpreter object for the execution of the traces
     * @return list of error location pairs
     */

    @Override
    public ErrorLocalisation localizeErrors(Program submittedProgram, Program referenceProgram,
                                            List<Input> inputs, String functionName, StructuralMapping structuralMapping,
                                            VariableMapping variableMapping, Interpreter interpreter) {
        throw new NotImplementedException();
    }

}
