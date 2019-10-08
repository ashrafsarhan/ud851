package com.udacity.bakingapp;

import android.app.Instrumentation;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.udacity.bakingapp.activity.RecipeActivity;
import com.udacity.bakingapp.utils.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Activity.RESULT_OK;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityIntentTest {

    @Rule
    public IntentsTestRule intentsTestRule = new IntentsTestRule<>(RecipeActivity.class);

    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(
                RESULT_OK, null));
    }

    @Test
    public void clickRecyclerViewItem_toOpenRecipeDetailsActivity() {
        onView(withId(R.id.rv_recipeCard)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasExtraWithKey(Constants.SELECTED_RECIPE_TO_SEE_DETAILS));
    }
}
