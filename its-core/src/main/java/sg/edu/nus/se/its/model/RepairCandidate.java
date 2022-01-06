package sg.edu.nus.se.its.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javatuples.Pair;
import org.javatuples.Triplet;

/**
 * Represents one possible repair repair at one location for a given possible variable mapping.
 */
public class RepairCandidate {

  public int mappingId; // unique ID representing a valid bijective pairing of the variables
  public float cost; // cost associated with that particular fix
  public List<Integer> order; // used to figure out order of repair

  // variable to repair, expression in incorrect program,
  // followed by expression in correct program
  public Triplet<Variable, Expression, Expression> repairedVariable;
  public Pair<Integer, Integer> errorLocation; // (mapping of the erroneous blocks)
  public String funcName; // name of function being fixed

  /**
   * Constructor that represents one possible repair candidate. The repair candidate may not always
   * be valid, verification to be done by repair algorithm.
   *
   * @param mapping Mapping of variables for particular repair
   * @param cost incurred for particular repair
   * @param repairedVariable variable to be repaired with the repaired expression
   * @param errorLocation error location repair should take place in
   * @throws Exception when error location or mapping is not valid
   */
  public RepairCandidate(List<Pair<Variable, Variable>> mapping, float cost,
      Triplet<Variable, Expression, Expression> repairedVariable, String funcName,
      Pair<Integer, Integer> errorLocation) throws Exception {
    this.mappingId = getMappingId(mapping);
    this.cost = cost;
    this.repairedVariable = repairedVariable;
    this.errorLocation = errorLocation;
    this.funcName = funcName;
  }

  /**
   * Gets unique mapping id for encoding.
   *
   * @param mapping Bijective list of all variable mapping
   * @return unique int id denoting the variable mapping
   * @throws Exception when mapping does not exist
   */
  public static int getMappingId(List<Pair<Variable, Variable>> mapping) throws Exception {
    Map<Variable, Variable> hashMapping = hashListToMap(mapping);
    VariableMapping varMatch = new VariableMapping(hashMapping);
    for (VariableMapping validVarMatch : VariableMapping.varMatches) {
      if (validVarMatch.getMatching().equals(varMatch.getMatching())) {
        return VariableMapping.varMatches.indexOf(validVarMatch);
      }
    }
    throw new Exception("mapping does not exist");
  }

  private static Map<Variable, Variable> hashListToMap(List<Pair<Variable, Variable>> mapping) {
    Map<Variable, Variable> hashMapping = new HashMap<>();
    for (Pair<Variable, Variable> map : mapping) {
      hashMapping.put(map.getValue0(), map.getValue1());
    }
    return hashMapping;
  }

  /**
   * Generates pretty printed String representation of the given list of repair candidates.
   * TODO YN: needs to be removed and ported to simple feedback module.
   */
  public static String toString(List<RepairCandidate> listOfRepairCandidates) {
    StringBuilder prettyPrintRepair = new StringBuilder();
    for (RepairCandidate repairCandidate : listOfRepairCandidates) {
      prettyPrintRepair.append(toString(repairCandidate));
    }
    String result = prettyPrintRepair.toString();
    // remove trailing \n
    if (result.length() > 1 && result.endsWith("\n")) {
      result = result.substring(0, result.length() - 1);
    }
    return result;
  }

  /**
   * Generates pretty printed String representation of the given repair candidates.
   */
  public static String toString(RepairCandidate repairCandidate) {
    StringBuilder prettyPrintRepair = new StringBuilder();
    Expression expr1 = repairCandidate.repairedVariable.getValue1();
    Expression expr2 = repairCandidate.repairedVariable.getValue2();
    Variable var = repairCandidate.repairedVariable.getValue0();
    Integer loc1 = repairCandidate.errorLocation.getValue1();

    if (expr1 != null && expr2 != null) {
      prettyPrintRepair.append("Change ").append(var).append(" = ").append(prettyPrintExpr(expr1))
          .append(" to ").append(var).append(" = ").append(prettyPrintExpr(expr2));

    } else if (expr1 == null) {
      // need to delete expression
      prettyPrintRepair.append("Add ").append(var).append(" = ").append(prettyPrintExpr(expr2));
    } else {
      prettyPrintRepair.append("Delete ").append(var).append(" = ").append(prettyPrintExpr(expr1));
    }
    prettyPrintRepair.append(" at location ").append(loc1);
    prettyPrintRepair.append("\n");
    return prettyPrintRepair.toString();
  }

  private static String prettyPrintExpr(Expression expr) {
    if (expr instanceof Constant) {
      return ((Constant) expr).getValue();
    } else if (expr instanceof Variable) {
      String name = expr.toString();
      return Variable.isPrimedName(name) ? Variable.asUnprimedVariableName(name) : name;
    } else if (expr instanceof Operation) {
      if (((Operation) expr).getName().equals("ite")) {
        return prettyPrintIteExpr((Operation) expr);
      }
      List<Expression> args = ((Operation) expr).getArgs();
      StringBuilder prettyPrint = new StringBuilder();
      for (Expression arg : args) {
        prettyPrint.append(prettyPrintExpr(arg));
        prettyPrint.append(" ").append(((Operation) expr).getName()).append(" ");
      }

      return prettyPrint.substring(0, prettyPrint.length() - 3);
    }
    return " ";
  }

  /**
   * Print out special case of if-then statements.
   *
   * @param expr Operation 'ite'
   * @return Representative version of
   */
  private static String prettyPrintIteExpr(Operation expr) {
    List<Expression> args = expr.getArgs();
    StringBuilder prettyPrint = new StringBuilder();
    prettyPrint.append("ite(");
    for (Expression arg : args) {
      prettyPrint.append(prettyPrintExpr(arg));
      prettyPrint.append(", ");
    }
    prettyPrint.delete(prettyPrint.length() - 2, prettyPrint.length()); // remove trailing ", "
    prettyPrint.append(")");

    return prettyPrint.toString();
  }

}
