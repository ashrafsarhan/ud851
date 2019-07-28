package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String NEW_LINE_DELIM = "\n";
    private static final String HYPHEN_DELIM ="- " ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.food_placeholder) // can also be a drawable
                .error(R.drawable.food_error_placeholder) // will be displayed if the image cannot be loaded
                .noFade()
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        TextView description = findViewById(R.id.description);
        description.append(sandwich.getDescription());

        TextView alsoKnown = findViewById(R.id.also_known);
        alsoKnown.append(getUnorderedListStr(sandwich.getAlsoKnownAs()));

        TextView ingredients = findViewById(R.id.ingredients);
        ingredients.append(getUnorderedListStr(sandwich.getIngredients()));

        TextView origin = findViewById(R.id.origin);
        origin.append(sandwich.getPlaceOfOrigin());

    }

    private String getUnorderedListStr(List<String> stringList) {
        List<String> stringListWithHyphen = new ArrayList<>();
        for (String s:stringList) {
            stringListWithHyphen.add(HYPHEN_DELIM.concat(s));
        }
        return TextUtils.join(NEW_LINE_DELIM, stringListWithHyphen);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
