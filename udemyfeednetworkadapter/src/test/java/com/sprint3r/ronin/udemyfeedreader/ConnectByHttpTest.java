package com.sprint3r.ronin.udemyfeedreader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectByHttpTest {

    UdemyFeedNetworkAdapter connectByHttp;

    public ConnectByHttpTest(){
        connectByHttp = new ConnectByHttp();
    }

    @Test
    public void TestGetFeed(){
        assertEquals("289230", connectByHttp.getFeed().get(0).getAsJsonObject().get("id").getAsString());
    }

}
