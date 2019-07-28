package com.udacity.sandwichclub.utils;

import android.util.Log;
import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {
        try {
            Sandwich sandwich = new Sandwich();
            JSONObject sandwichJson = new JSONObject(json);
            sandwich.setMainName(sandwichJson.getJSONObject("name").getString("mainName"));
            sandwich.setAlsoKnownAs(toStringList(sandwichJson.getJSONObject("name").getJSONArray("alsoKnownAs")));
            sandwich.setPlaceOfOrigin(sandwichJson.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichJson.getString("description"));
            sandwich.setImage(sandwichJson.getString("image"));
            sandwich.setIngredients(toStringList(sandwichJson.getJSONArray("ingredients")));
            Log.d(TAG, "Sandwich object: ".concat(sandwich.toString()));
            return sandwich;
        } catch (JSONException e) {
            Log.e(TAG, "Error while parsing JSON: ".concat(e.getMessage()));
            return null;
        }
    }

    private static List<String> toStringList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>();
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i=0;i<len;i++){
                stringList.add(jsonArray.get(i).toString());
            }
        }
        return stringList;
    }
}
