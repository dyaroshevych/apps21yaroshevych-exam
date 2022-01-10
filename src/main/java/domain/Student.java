package domain;

import json.JsonObject;
import json.JsonString;
import json.JsonNumber;
import json.JsonPair;
import json.JsonBoolean;
import json.JsonArray;
import json.Tuple;


import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private ArrayList<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);

        this.exams = new ArrayList<>();
        for (Tuple<String, Integer> exam : exams) {
            this.exams.add(exam);
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonData = super.toJsonObject();
        JsonObject[] examsData = new JsonObject[this.exams.size()];
        for (int idx = 0; idx < this.exams.size(); idx++) {
            JsonString course = new JsonString(exams.get(idx).key);
            JsonNumber mark = new JsonNumber(exams.get(idx).value);
            JsonBoolean passed = new JsonBoolean(exams.get(idx).value.compareTo(2) > 0);

            examsData[idx] = new JsonObject(
                                new JsonPair("course", course),
                                new JsonPair("mark", mark),
                                new JsonPair("passed", passed)
            );
        }

        jsonData.add(new JsonPair("exams", new JsonArray(examsData)));

        return jsonData;
    }
}