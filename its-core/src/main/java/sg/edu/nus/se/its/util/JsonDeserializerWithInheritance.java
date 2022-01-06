package sg.edu.nus.se.its.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;

/**
 * Custom JSON Deserializer to incorporate class inheritance.
 */
public class JsonDeserializerWithInheritance<T> implements JsonDeserializer<T> {

  @Override
  public T deserialize(
      JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    JsonPrimitive classNamePrimitive = (JsonPrimitive) jsonObject.get("tokentype");

    String className = "sg.edu.nus.se.its.model." + classNamePrimitive.getAsString();

    Class<?> clazz;
    try {
      clazz = Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new JsonParseException(e.getMessage());
    }
    return context.deserialize(jsonObject, clazz);
  }
}
