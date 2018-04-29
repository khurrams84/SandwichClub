package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json)
    {
        Sandwich sandwich = new Sandwich();


        if(json != null) {
            try{
                JSONObject jsonObj = new JSONObject(json);

                JSONObject jsonName = new JSONObject(jsonObj.getString("name"));
                sandwich.setMainName(jsonName.getString("mainName"));
                JSONArray jsonAlsoKnownAsArray = jsonName.getJSONArray("alsoKnownAs");

                List<String> alsoKnownAsList = new ArrayList<String>();
                for(int i=0;i<jsonAlsoKnownAsArray.length();i++){
                    alsoKnownAsList.add(jsonAlsoKnownAsArray.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoKnownAsList);

                sandwich.setPlaceOfOrigin(jsonObj.getString("placeOfOrigin"));
                sandwich.setDescription(jsonObj.getString("description"));
                sandwich.setImage(jsonObj.getString("image"));

                JSONArray jsonIngredientsArray = jsonObj.getJSONArray("ingredients");
                List<String> ingredientsList = new ArrayList<String>();
                for(int i=0;i<jsonIngredientsArray.length();i++){
                    ingredientsList.add(jsonIngredientsArray.getString(i));
                }
                sandwich.setIngredients(ingredientsList);

            }
            catch (final JSONException e) {
                return null;
            }
        }

        return sandwich;
    }
}
