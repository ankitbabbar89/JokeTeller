package com.ankitb.joketeller.main;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    String mResult = null;
    CountDownLatch signal = null;
    Exception mException = null;
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        if(signal != null)
            signal.countDown();
    }

    public void testFetchJokeTask() throws InterruptedException{
        Context context = getContext();
        FetchJokeTask task = new FetchJokeTask();
        task.setListener(new FetchJokeTask.FetchJokeTaskListener() {
            @Override
            public void onComplete(String result, Exception e) {
                mResult = result;
                mException = e;
                signal.countDown();
            }
        });

        task.execute(context);

        signal.await();
        assertNull(mException);
        assertFalse(TextUtils.isEmpty(mResult));

    }
}
