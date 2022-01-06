package sg.edu.nus.se.its.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.javatuples.Pair;
import sg.edu.nus.se.its.model.Expression;
import sg.edu.nus.se.its.model.Input;
import sg.edu.nus.se.its.model.Program;

/**
 * File loading utility method to load inputs and intermediate representations for testing purposes.
 */
public class TestUtils {

  /**
   * Loads the internal program representation from a stored .json file (old format).
   *
   * @param name -- source file name, e.g., "arith.c"
   * @return Program object
   */
  @Deprecated
  public static Program loadProgramByNameOld(String name) {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Expression.class, new JsonSerializerWithInheritance<Expression>());
    builder.registerTypeAdapter(new TypeToken<Pair<String, Expression>>() {}.getType(),
            new PairDeserializer<>(String.class, Expression.class));
    builder.registerTypeAdapter(new TypeToken<Pair<String, String>>() {}.getType(),
            new PairDeserializer<>(String.class, String.class));
    Gson gson = builder.create();
    File modelFile = new File("../its-core/src/test/resources/model/" + name + ".json");
    try {
      return gson.fromJson(new FileReader(modelFile), Program.class);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Loads the internal program representation from a stored .json file (old format).
   *
   * @param filePath -- full file path for the json model file, e.g., "arith.c.json"
   * @return Program object
   */
  @Deprecated
  public static Program loadProgramByFilePathOld(String filePath) {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Expression.class, new JsonSerializerWithInheritance<Expression>());
    builder.registerTypeAdapter(new TypeToken<Pair<String, Expression>>() {}.getType(),
            new PairDeserializer<>(String.class, Expression.class));
    builder.registerTypeAdapter(new TypeToken<Pair<String, String>>() {}.getType(),
            new PairDeserializer<>(String.class, String.class));
    Gson gson = builder.create();

    File modelFile = new File(filePath);
    try {
      return gson.fromJson(new FileReader(modelFile), Program.class);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Converts a JSON file that represents a Program object in the old format, to a JSON file with
   * the new format.
   *
   * @param filePathOldJsonFormatFile - file path of the JSON file in the old format
   * @param newFilePath - file path for the JSON file in the new format
   * @return success flag
   */
  public static boolean convertProgramJsonFile(String filePathOldJsonFormatFile,
                                               String newFilePath) {
    Program program = loadProgramByFilePathOld(filePathOldJsonFormatFile);
    return storeProgramAsJsonFile(program, newFilePath);
  }

  /**
   * Stores given program in the JSON format.
   *
   * @param program - Program
   * @param filePath - String
   * @return success
   */
  public static boolean storeProgramAsJsonFile(Program program, String filePath) {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Expression.class, new JsonSerializerWithInheritance<Expression>());
    builder.setPrettyPrinting();
    Gson gson = builder.create();
    String value = gson.toJson(program);

    try {
      FileWriter myWriter = new FileWriter(filePath);
      myWriter.write(value);
      myWriter.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Loads the Program model from the JSON format into the Program object. This version of loading
   * does not match Clara's format.
   *
   * @param filePath - String
   * @return Program object
   */
  public static Program loadProgramByFilePath(String filePath) {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Expression.class, new JsonSerializerWithInheritance<Expression>());
    Gson gson = builder.create();
    File modelFile = new File(filePath);
    try {
      return gson.fromJson(new FileReader(modelFile), Program.class);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Loads the Program model from the JSON format into the Program object. This version of loading
   * does not match Clara's format.
   *
   * @param name - String
   * @return Program object
   */
  public static Program loadProgramByName(String name) {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Expression.class, new JsonSerializerWithInheritance<Expression>());
    Gson gson = builder.create();

    File modelFile = new File("../its-core/src/test/resources/model/" + name + ".json");

    try {
      return gson.fromJson(new FileReader(modelFile), Program.class);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static File loadFileRessourceByName(String name) {
    return new File("../its-core/src/test/resources/source/" + name);
  }

  /**
   * Loads the inputs for a specific test case.
   *
   * @param name -- source file name
   * @return List of inputs as String objects
   */
  public static List<Input> loadInputsByProgramName(String name) {
    return loadInputsByProgramName(name, false);
  }

  /**
   * Loads the inputs for a specific test case.
   *
   * @param name -- source file name
   * @param ignoreException -- boolean flag
   * @return List of inputs as String objects
   */
  public static List<Input> loadInputsByProgramName(String name, boolean ignoreException) {
    File inputFile = new File("../its-core/src/test/resources/input/" + name + ".in");
    try {
      Scanner reader = new Scanner(inputFile);
      List<String> result = new ArrayList<>();
      while (reader.hasNext()) {
        result.add(reader.next());
      }
      reader.close();
      String[] ioInputs = result.toArray(new String[result.size()]);
      return Arrays.asList(new Input(ioInputs, null));
    } catch (FileNotFoundException e) {
      if (ignoreException) {
        return Arrays.asList(new Input(null, null));
      } else {
        e.printStackTrace();
        return null;
      }

    }
  }

  /**
   * Loads the inputs for a specific programming task.
   *
   * @param name -- Python task name
   * @return List of inputs as String objects
   */
  public static List<String> loadInputsByPythonTaskName(String name) {
    File inputFiles = new File("../its-core/src/test/resources/python/input/" + name + "/");
    FileFilter fileFilter = new WildcardFileFilter("input_*.txt");
    File[] files = inputFiles.listFiles(fileFilter);
    Arrays.sort(files);
    List<String> result = new ArrayList<>();
    try {
      for (File file : files) {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
          result.add(reader.nextLine());
        }
        reader.close();
      }
      return result;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Loads test inputs for Python interpreter.
   *
   * @param name - task name
   * @return list of inputs
   */
  public static List<Input> loadInputsForInterpreterByPythonTaskName(String name) {
    // Map<Integer, String>>
    File inputFiles = new File("../its-core/src/test/resources/python/input/" + name + "/");
    FileFilter fileFilter = new WildcardFileFilter("simplified_input_*.txt");
    File[] files = inputFiles.listFiles(fileFilter);
    Arrays.sort(files);
    List<Input> listOfInputs = new ArrayList<>();
    try {
      for (File file : files) {
        Scanner reader = new Scanner(file);
        List<String> tmpList = new ArrayList<>();
        while (reader.hasNextLine()) {
          tmpList.add(reader.nextLine());
        }
        String[] args = tmpList.toArray(new String[tmpList.size()]);
        Input i = new Input(null, args);
        listOfInputs.add(i);
        reader.close();
      }
      return listOfInputs;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Loads File objects for Python test task.
   *
   * @param name - task name
   * @return array of File objects
   */
  public static File[] loadProgramsByPythonTaskName(String name) {
    File inputFiles = new File("../its-core/src/test/resources/python/source/" + name + "/");
    FileFilter fileFilter = new WildcardFileFilter("correct_*_*.py");
    return inputFiles.listFiles(fileFilter);
  }

}
