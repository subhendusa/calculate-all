package com.sekhar.android.calculator;

/**
 * Created by sekhar on 31-07-2016.
 */

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NumericOperationTest {

    @Rule
    public ActivityTestRule<BasicCalculator> mActivityRule = new ActivityTestRule<>(
            BasicCalculator.class);

    @Before
    public void setUp() {
    }

    @Test
    public void addTwoNumbers() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1+2")));
        onView(withId(R.id.txtResult)).check(matches(withText("3")));
    }

    @Test
    public void enterTextAtDifferentCursorPosition() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());

        onView(withId(R.id.btnAdd)).perform(click());

        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnFour)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_DPAD_LEFT));
        onView(withId(R.id.btnAdd)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12+3+4")));
        onView(withId(R.id.txtResult)).check(matches(withText("19")));

        onView(withId(R.id.btnFive)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12+3+54")));
        onView(withId(R.id.txtResult)).check(matches(withText("69")));
    }
}
