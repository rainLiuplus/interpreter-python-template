package sg.edu.nus.se.its.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.javatuples.Pair;

/**
 * Represents variable mapping between two different programs. This includes variables that has to
 * be added/deleted.
 *
 */
public class VariableMapping {

  /**
   * varMatches contains a list of all the possible bijective variables between reference and
   * incorrect program. varMatches should be cleared after every successful repair. Bijectivity of
   * variables is assumed.
   */
  public static List<VariableMapping> varMatches = new ArrayList<>();

  /**
   * matching contains ONE set of bijective variable mapping between the reference and incorrect
   * programs.
   */
  private Map<Variable, Variable> matching;

  public VariableMapping(Map<Variable, Variable> matching) {
    this.matching = matching;
  }

  public Map<Variable, Variable> getMatching() {
    return matching;
  }

  public Variable getMatchingVar(Variable var) {
    return matching.get(var);
  }

  /**
   * Responsible to getting the variable from the incorrect program given the variable from the
   * reference program.
   *
   * @param var name of variable in reference program
   * @return mapped variable, returns null if variable is not mapped
   */
  public Variable getMatchingVar(String var) {
    String unprimedVariableName =
        Variable.isPrimedName(var) ? Variable.asUnprimedVariableName(var) : var;
    for (Map.Entry<Variable, Variable> entry : matching.entrySet()) {
      // if (entry.getKey().toString().equals(var) || entry.getKey().toString().equals(var + "'")) {
      if (entry.getKey().toString().equals(unprimedVariableName) || entry.getKey().toString()
          .equals(Variable.asPrimedVariableName(unprimedVariableName))) {
        return entry.getValue();
      }
    }
    return null;
  }

  /**
   * Helper method to get all possible bijective variable mappings, just in a iterable object.
   *
   * @return List of all possible variable mappings
   */
  public static List<List<Pair<Variable, Variable>>> getAllVarMatches() {
    List<List<Pair<Variable, Variable>>> varList = new ArrayList<>();
    for (VariableMapping varMatch : varMatches) {
      List<Pair<Variable, Variable>> pairing = new ArrayList<>();
      for (Map.Entry<Variable, Variable> entrySet : varMatch.matching.entrySet()) {
        pairing.add(Pair.with(entrySet.getKey(), entrySet.getValue()));
      }
      varList.add(pairing);
    }
    return varList;
  }

}
