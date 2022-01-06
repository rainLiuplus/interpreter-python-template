package sg.edu.nus.se.its.alignment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import sg.edu.nus.se.its.model.Variable;

/**
 * Stores the variable mapping between two programs. Assumes that a structural mapping does exist.
 */
public class VariableMapping {

  /**
   * Maps a functionName as String to a Map of Integer mappings for the locations in the two
   * programs.
   */
  private Map<String, Map<Variable, Variable>> mappingByName;

  /**
   * Creates an empty variable mapping.
   */
  public VariableMapping() {
    this.mappingByName = new HashMap<>();
  }

  public Map<Variable, Variable> put(String functionName,
                                     Map<Variable, Variable> internalVariableMapping) {
    return mappingByName.put(functionName, internalVariableMapping);
  }

  public Map<String, Map<Variable, Variable>> getAllMappings() {
    return mappingByName;
  }

  public Map<Variable, Variable> getMapping(String functionName) {
    return mappingByName.get(functionName);
  }

  public Variable getMatchingVariable(String functionaName, Variable variableName) {
    return Optional.ofNullable(mappingByName.get(functionaName))
        .map(varMatch -> varMatch.get(variableName)).orElse(null);
  }

}
