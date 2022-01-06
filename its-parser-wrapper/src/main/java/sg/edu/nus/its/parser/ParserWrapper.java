package sg.edu.nus.its.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import org.javatuples.Pair;
import sg.edu.nus.se.its.model.Expression;
import sg.edu.nus.se.its.model.Program;
import sg.edu.nus.se.its.parser.Parser;
import sg.edu.nus.se.its.util.JsonSerializerWithInheritance;
import sg.edu.nus.se.its.util.PairDeserializer;
import sg.edu.nus.se.its.util.TestUtils;

/**
 * Concrete parser implementation for both C and Python. The parser requests are relayed to Clara.
 */
public class ParserWrapper implements Parser {

  static final int PARSING_TIMEOUT = 1000; // milliseconds

  @Override
  public Program parse(File filePath) throws IOException {

    if (filePath == null || !filePath.exists()) {
      throw new RuntimeException("Parsing Error: Provided file does not exist!");
    }

    String workingDir = System.getProperty("user.dir");
    File tmpFile = File.createTempFile("program", ".json");

    String command = "python3 " + workingDir + "/../its-parser-wrapper"
        + "/src/main/resources/CLARA/Main.py jsonmodel " + filePath;

    BufferedWriter writer = null;

    try {
      Process proc = Runtime.getRuntime().exec(command);
      proc.waitFor(PARSING_TIMEOUT, TimeUnit.MILLISECONDS);

      int exitCode = proc.exitValue();
      if (exitCode != 0) {
        throw new RuntimeException("Parsing Error: unexpected python script exit code " + exitCode);
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      writer = new BufferedWriter(new FileWriter(tmpFile, true));
      String line;
      while ((line = reader.readLine()) != null) {
        writer.write(line);
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        writer.close();
      }
    }

    Program prog = TestUtils.loadProgramByFilePathOld(tmpFile.getAbsolutePath());
    tmpFile.deleteOnExit();
    return prog;
  }

}
