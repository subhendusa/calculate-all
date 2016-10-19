package com.sekhar.android.calculator;

/**
 * Created by sekhar on 06-08-2016.
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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TextInsertionTest {
    @Rule
    public ActivityTestRule<BasicCalculator> mActivityRule = new ActivityTestRule<>(
            BasicCalculator.class);

    @Before
    public void setUp() {}

    @Test
    public void enterNumbers() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1")));

        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12")));

        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("123")));

        onView(withId(R.id.btnFour)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1234")));

        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12345")));

        onView(withId(R.id.btnSix)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("123456")));

        onView(withId(R.id.btnSeven)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1234567")));

        onView(withId(R.id.btnEight)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12345678")));

        onView(withId(R.id.btnNine)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("123456789")));

        onView(withId(R.id.btnZero)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1234567890")));

        onView(withId(R.id.btnDot)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1234567890.")));
    }

    @Test
    public void enterOperators() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1+")));

        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.btnSub)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1+2-")));

        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnMult)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1+2-3*")));

        onView(withId(R.id.btnFour)).perform(click());
        onView(withId(R.id.btnDiv)).perform(click());
        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("1+2-3*4/")));
    }

    @Test
    public void enterOnlyOperator() {
        onView(withId(R.id.btnAdd)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("")));
        onView(withId(R.id.txtResult)).check(matches(withText("0")));
    }

    @Test
    public void enterTwoOperators() {
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnSub)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("")));
        onView(withId(R.id.txtResult)).check(matches(withText("0")));
    }

    @Test
    public void enterTwoOperatorsAfterNumbers() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());

        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnSub)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12+")));
        onView(withId(R.id.txtResult)).check(matches(withText("12")));
    }

    @Test
    public void enterTwoOperatorsChangingCursor() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());

        onView(withId(R.id.btnAdd)).perform(click());

        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnFour)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_DPAD_LEFT));
        onView(withId(R.id.btnAdd)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12+3+4")));

        onView(withId(R.id.btnSub)).perform(click());

        onView(withId(R.id.txtCalcDisplay)).check(matches(withText("12+3+4")));
        onView(withId(R.id.txtResult)).check(matches(withText("19")));
    }
}
