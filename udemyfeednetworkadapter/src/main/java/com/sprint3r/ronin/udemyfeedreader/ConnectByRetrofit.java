package com.sprint3r.ronin.udemyfeedreader;

import com.google.gson.JsonArray;

public class ConnectByRetrofit implements UdemyFeedNetworkAdapter{

    final private String API_URL = "https://www.udemy.com";
    
    @Override
    public JsonArray getFeed() {
        return null;
    }
}
