package com.sekhar.android.calculator;

/**
 * Created by sekhar on 31-07-2016.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviorTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() {
    }

    @Test
    public void changeDisplayText() {
        final String testString = "Espresso";

        onView(withId(R.id.txtCalcDisplay)).perform(typeText(testString));
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText(testString)));
    }

    @Test
    public void enterNumbers() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1")));
    }

    @Test
    public void enterTwoNumbers() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12")));
        onView(withId(R.id.txtResult)).check(matches(withText("12.0")));
    }

    @Test
    public void addTwoNumbers() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1+2")));
        onView(withId(R.id.txtResult)).check(matches(withText("3.0")));
    }
}
