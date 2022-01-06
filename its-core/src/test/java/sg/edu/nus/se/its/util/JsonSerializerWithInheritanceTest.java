package sg.edu.nus.se.its.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import sg.edu.nus.se.its.model.Expression;
import sg.edu.nus.se.its.model.Program;

/**
 * Tests the custom de-/serialization.
 */
public class JsonSerializerWithInheritanceTest {

  @Test
  void test_EmptyProgram() {

    Program sourceProgram = new Program();

    Gson gsonSerializer = new GsonBuilder()
        .registerTypeAdapter(Program.class, new JsonSerializerWithInheritance<Program>()).create();
    String json = gsonSerializer.toJson(sourceProgram);

    assertEquals("{\"importStatements\":[],\"fncs\":{},\"meta\":{},\"warns\":{},"
            + "\"loops\":{},\"tokentype\":\"Program\"}",
        json);


    Gson gsonDeserializer = new GsonBuilder()
        .registerTypeAdapter(Expression.class, new JsonDeserializerWithInheritance<Expression>())
        .registerTypeAdapter(new TypeToken<Pair<String, Expression>>() {}.getType(),
            new PairDeserializer<>(String.class, Expression.class))
        .registerTypeAdapter(new TypeToken<Pair<String, String>>() {}.getType(),
            new PairDeserializer<>(String.class, String.class))
        .create();

    Program targetProgram = gsonDeserializer.fromJson(json, Program.class);

    assertEquals(sourceProgram.getFncs(), targetProgram.getFncs());
    assertEquals(sourceProgram.getLoops(), targetProgram.getLoops());
    assertEquals(sourceProgram.getMeta(), targetProgram.getMeta());
    assertEquals(sourceProgram.getWarns(), targetProgram.getWarns());
  }

}
