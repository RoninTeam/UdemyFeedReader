package com.sprint3r.ronin.udemyfeedreader;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static org.junit.Assert.assertEquals;

public class RetrofitApiTest {

    private RetrofitApi retrofitApi;
    private CoursesDetail feedUdemy;
    private Call<CoursesDetail> feed;


    @Test
    public void testCallNextPageUrl() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        retrofitApi = new RetrofitApi();
        feed = retrofitApi.getData();

        feed.enqueue(new Callback<CoursesDetail>() {
            @Override
            public void onResponse(Response<CoursesDetail> response) {

                feedUdemy = response.body();
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
        countDownLatch.await();
        assertEquals("https://www.udemy.com/api-2.0/courses?page=2", feedUdemy.next);
    }

    @Test
    public void testCallTitleResults() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        retrofitApi = new RetrofitApi();
        feed = retrofitApi.getData();

        feed.enqueue(new Callback<CoursesDetail>() {
            @Override
            public void onResponse(Response<CoursesDetail> response) {

                feedUdemy = response.body();
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
        countDownLatch.await();
        String expect = "Learn and Understand AngularJS";
        assertEquals(expect, feedUdemy.results.get(0).getAsJsonObject().get("title").getAsString());
    }
}