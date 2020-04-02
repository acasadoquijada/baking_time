package com.example.backing_app;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.backing_app.fragment.StepListFragment.RECIPE_INDEX_KEY;
import static com.example.backing_app.fragment.StepListFragment.STEP_INDEX_KEY;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class StepDetailActivityTest {

    private static final String CURRENT_STEP_TEXT = "3. Press the cookie crumb mixture into the " +
            "prepared pie pan and bake for 12 minutes. Let crust cool to room temperature.";

    private static final String PREVIOUS_STEP_TEXT = "2. Whisk the graham cracker crumbs, 50 grams" +
            " (1/4 cup) of sugar, and 1/2 teaspoon of salt together in a medium bowl." +
            " Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients" +
            " and stir together until evenly mixed.";

    private static final String NEXT_STEP_TEXT = "4. Beat together the nutella, mascarpone," +
            " 1 teaspoon of salt, and 1 tablespoon of vanilla on medium speed in a stand mixer or " +
            "high speed with a hand mixer until fluffy.";

    private static final String FIRST_STEP_TEXT = "Recipe Introduction";

    private static final String LAST_STEP_TEXT = "6. Pour the filling into the prepared crust and " +
            "smooth the top. Spread the whipped cream over the filling. Refrigerate the pie for " +
            "at least 2 hours. Then it's ready to serve!";

    private static final int STEP_NUMBER = 3;
    private static final int RECIPE_INDEX = 1;

    @Rule
    public ActivityTestRule<StepDetailActivity> recipeDetailRule =
            new ActivityTestRule<>(StepDetailActivity.class,true,false);

    @Before
    public void setup(){
        Intent intent = new Intent();
        intent.putExtra(STEP_INDEX_KEY, STEP_NUMBER);
        intent.putExtra(RECIPE_INDEX_KEY, RECIPE_INDEX);
        recipeDetailRule.launchActivity(intent);
    }

    @Test
    public void stepInstruction_ContainsCorrectDescription(){

        onView(allOf(withId(R.id.step_description), isDescendantOfA(withId(R.id.step_description_frame_layout))))
                .check(matches(withText(CURRENT_STEP_TEXT)));
    }

    @Test
    public void previousButton_OpenPreviousStep(){

        // Find the button and perform click

        onView(withId(R.id.previous_button)).perform(click());

        onView(allOf(withId(R.id.step_description), isDescendantOfA(withId(R.id.step_description_frame_layout))))
                .check(matches(withText(PREVIOUS_STEP_TEXT)));

    }

    @Test
    public void nextButton_OpenNextStep(){

        // Find the button and perform click

        onView(withId(R.id.next_button)).perform(click());

        onView(allOf(withId(R.id.step_description), isDescendantOfA(withId(R.id.step_description_frame_layout))))
                .check(matches(withText(NEXT_STEP_TEXT)));

    }

    @Test
    public void previousButton_DoesntOpenPreviousStepIfCurrentStepIsFirstStep(){

        // We are in the third step. Click previous button 3 times
        // We move as follows:
        // Second step, first step, first step again

        for(int i = 0; i < 3; i++){
            onView(withId(R.id.previous_button)).perform(click());
        }

        onView(allOf(withId(R.id.step_description), isDescendantOfA(withId(R.id.step_description_frame_layout))))
                .check(matches(withText(FIRST_STEP_TEXT)));
    }

    @Test
    public void nextButton_DoesntOpenNextStepIfCurrentStepIsLastStep(){

        // We are in the third step. Click previous button 3 times
        // We move as follows:
        // Fourth step, fifth step, sixth step, sixth step again

        for(int i = 0; i < 4; i++){
            onView(withId(R.id.next_button)).perform(click());
        }

        onView(allOf(withId(R.id.step_description), isDescendantOfA(withId(R.id.step_description_frame_layout))))
                .check(matches(withText(LAST_STEP_TEXT)));
    }
}