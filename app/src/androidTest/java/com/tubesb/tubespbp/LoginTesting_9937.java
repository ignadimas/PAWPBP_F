package com.tubesb.tubespbp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTesting_9937 {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void loginTesting_9937() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_container),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.etEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.etEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("paw"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_container),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.etEmail), withText("paw"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("pawcoba@gmail.com"));

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.etEmail), withText("pawcoba@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_container),
                                        0),
                                4),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.etPassword),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_container),
                                        0),
                                4),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.etPassword), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("123456"));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.etPassword), withText("123456"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.etEmail), withText("pawcoba@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("pawcoba1@gmail.com"));

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.etEmail), withText("pawcoba1@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_container),
                                        0),
                                4),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.etEmail), withText("pawcoba1@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(replaceText("pawcoba@gmail.com"));

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.etEmail), withText("pawcoba@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.etPassword), withText("123456"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(replaceText("1234561"));

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.etPassword), withText("1234561"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText13.perform(closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_container),
                                        0),
                                4),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.etPassword), withText("1234561"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText14.perform(replaceText("123456"));

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.etPassword), withText("123456"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText15.perform(closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnLogin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_container),
                                        0),
                                4),
                        isDisplayed()));
        materialButton7.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
