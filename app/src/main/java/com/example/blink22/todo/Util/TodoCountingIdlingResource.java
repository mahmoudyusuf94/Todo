package com.example.blink22.todo.Util;

import android.support.test.espresso.idling.CountingIdlingResource;

public class TodoCountingIdlingResource {

    private static CountingIdlingResource mCountingIdlingResource
            = new CountingIdlingResource("Todo");

    public static CountingIdlingResource getCountingIdlingResource(){
        return mCountingIdlingResource;
    }

    public static void increment(){
        mCountingIdlingResource.increment();
    }

    public static void decrement(){
        mCountingIdlingResource.decrement();
    }

}
