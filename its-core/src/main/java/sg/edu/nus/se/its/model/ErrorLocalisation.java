package sg.edu.nus.se.its.model;

import java.util.HashMap;
import java.util.List;
import org.javatuples.Pair;

/**
 * Class to encapsulate the erroneous locations per function.
 * This error locations will be stored in a hashmap where format:
 * key: function name
 * value: a bijective set of mapping of error locations, where the first integer refers to the
 * reference program and the second integer refers to the location of the submitted program.
 */
public class ErrorLocalisation extends HashMap<String, List<Pair<Integer, Integer>>> {
  public ErrorLocalisation() {
    super();
  }
}
