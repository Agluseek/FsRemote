package com.agluseek.farsoon.farsoononline.utils;

import android.util.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ag1useek on 2017/7/31.
 */

public class Functions {
    public static List<Map<String, String>> readJson(String jsonStr) throws Exception {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        JsonReader reader = new JsonReader(new StringReader(jsonStr));
        reader.beginArray();

        while (reader.hasNext()) {
            Map oneresult = new HashMap();
            reader.beginObject();

            while(reader.hasNext()){
                String key = reader.nextName();
                String value = reader.nextString();
                oneresult.put(key, value);
            }
            result.add(oneresult);
            reader.endObject();
        }
        reader.endArray();
        return result;
    }
}
