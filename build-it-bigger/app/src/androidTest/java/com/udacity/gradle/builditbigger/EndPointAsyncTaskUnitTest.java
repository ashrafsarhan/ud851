package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import java.util.concurrent.ExecutionException;

public class EndPointAsyncTaskUnitTest extends ApplicationTestCase<Application> {

    public EndPointAsyncTaskUnitTest() {
        super(Application.class);
    }

    public void testGetJokeTask() throws ExecutionException, InterruptedException {
        EndPointAsyncTask testJoke = new EndPointAsyncTask(new EndPointAsyncTask.Callback() {
            @Override
            public void onFinished(String result) {

            }
        });
        testJoke.execute();
        String joke = testJoke.get();
        assertNotNull(joke);
        assertFalse(TextUtils.isEmpty(joke));
    }
}

