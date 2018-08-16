package com.example.blink22.todo;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.example.blink22.todo.ui.TodoList.TodoListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationBarTest {

    @Rule
    public ActivityTestRule<TodoListActivity> mTodoListActivityActivityTestRule
            = new ActivityTestRule<>(TodoListActivity.class);

    @Test
    public void clickNavigationIconOpensNaviagationMenu(){

        //check that the drawer layout is closed.
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)));

        String navigateUpDesc = mTodoListActivityActivityTestRule.getActivity()
                .getString(android.support.v7.appcompat.R.string.abc_action_bar_up_description);

        onView(withContentDescription(navigateUpDesc)).perform(click());

        onView(withId(R.id.drawer_layout)).check(matches(isOpen(Gravity.LEFT)));

    }


    @Test
    public void addNewTodoFromNavigationMenu(){

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.menu_item_add_todo));

        onView(withId(R.id.todo_done_button)).check(matches(isDisplayed()));

    }

}
