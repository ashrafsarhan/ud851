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
    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            Sandwich sandwich = new Sandwich();
            JSONObject sandwichJson = new JSONObject(json);
            sandwich.setMainName(sandwichJson.getJSONObject(NAME).getString(MAIN_NAME));
            sandwich.setAlsoKnownAs(toStringList(sandwichJson.getJSONObject(NAME).getJSONArray(ALSO_KNOWN_AS)));
            sandwich.setPlaceOfOrigin(sandwichJson.getString(PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichJson.getString(DESCRIPTION));
            sandwich.setImage(sandwichJson.getString(IMAGE));
            sandwich.setIngredients(toStringList(sandwichJson.getJSONArray(INGREDIENTS)));
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
