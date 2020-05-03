package cyc.interfaces.okHttp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bruce.Chen
 * @version 1.0
 * @date 2020/5/3 16:51
 */
public class JsonParse {
    public static String getJsonValue(String JsonString, String JsonId) {
        String JsonValue = "";
        if (JsonString == null || JsonString.trim().length() < 1) {
            return null;
        }
        try {
            JSONObject obj1 = new JSONObject(JsonString);
            JsonValue = (String) obj1.getString(JsonId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JsonValue;
    }
}
