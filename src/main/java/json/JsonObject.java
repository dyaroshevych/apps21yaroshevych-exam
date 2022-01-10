package json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Map<String, Json> data;

    public JsonObject(JsonPair... jsonPairs) {
        this.data = new HashMap<>();
        for (JsonPair pair : jsonPairs) {
            this.data.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        StringBuilder json = new StringBuilder();

        json.append("{");

        for (Map.Entry pair: this.data.entrySet()) {
            json.append("'");
            json.append((String) pair.getKey());
            json.append("': ");
            json.append(((Json) pair.getValue()).toJson());
            json.append(", ");
        }

        if (json.length() > 1) {
            json.setLength(json.length() - 2);
        }

        json.append("}");

        return json.toString();
    }

    public void add(JsonPair jsonPair) {
        this.data.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return data.get(name) != null ? data.get(name) : null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonData = new JsonObject();

        for (String name : names) {
            Json value = this.find(name);

            if (value != null) {
                jsonData.add(new JsonPair(name, value));
            }
        }

        return jsonData;
    }
}
