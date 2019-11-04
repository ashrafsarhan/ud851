package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EndPointAsyncTaskUnitTest extends ApplicationTestCase<Application> {

    private String joke;

    public EndPointAsyncTaskUnitTest() {
        super(Application.class);
    }

    @Test
    public void testGetJokeTask() throws ExecutionException, InterruptedException {
        EndPointAsyncTask endPointAsyncTask = new EndPointAsyncTask(new EndPointAsyncTask.Callback() {
            @Override
            public void onFinished(String result) {

            }
        });
        endPointAsyncTask.execute();
        joke = endPointAsyncTask.get();
        assertNotNull("Joke does not fetched successfully (null)", joke);
        assertFalse("Joke does not fetched successfully (empty)", joke.isEmpty());
    }
}

