package com.example.blink22.todo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.example.blink22.todo.Util.TodoCountingIdlingResource;
import com.example.blink22.todo.ui.TodoList.TodoListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class TodoListScreenTest {

    @Rule
    public ActivityTestRule<TodoListActivity> mTodoListActivityActivityTestRule
            = new ActivityTestRule<>(TodoListActivity.class);

    @Before
    public void setup(){
        Espresso.registerIdlingResources(TodoCountingIdlingResource.getCountingIdlingResource());
    }

    @Test
    public void clickAddTodo_OpenNewTodoView(){
        onView(withId(R.id.todo_list_fab)).perform(click());
        onView(withId(R.id.todo_done_button)).check(matches(isDisplayed()));
    }

    @Test
    public void addNewTodoToTodoList() throws InterruptedException {
        String title = "Todo Title"+ new Random().nextInt();
        String describtion = "Todo Describtion";

        onView(withId(R.id.todo_list_fab)).perform(click());
        onView(withId(R.id.todo_title_edit_text)).perform(typeText(title), closeSoftKeyboard());
        onView(withId(R.id.todo_desc_edit_text)).perform(typeText(describtion), closeSoftKeyboard());
        onView(withId(R.id.todo_done_button)).perform(click());

        onView(withText(title)).check(matches(isDisplayed()));
    }

    @Test
    public void addNewTodoViewDetails() throws InterruptedException {
        String title = "Todo Title"+ new Random().nextInt();
        String describtion = "Todo Describtion";

        onView(withId(R.id.todo_list_fab)).perform(click());
        onView(withId(R.id.todo_title_edit_text)).perform(typeText(title), closeSoftKeyboard());
        onView(withId(R.id.todo_desc_edit_text)).perform(typeText(describtion), closeSoftKeyboard());
        onView(withId(R.id.todo_done_button)).perform(click());

        onView(withText(title)).check(matches(isDisplayed()));

        onView(withText(title)).perform(click());
        onView(withText(title)).check(matches(isDisplayed()));
        onView(withText(describtion)).check(matches(isDisplayed()));
    }

}


