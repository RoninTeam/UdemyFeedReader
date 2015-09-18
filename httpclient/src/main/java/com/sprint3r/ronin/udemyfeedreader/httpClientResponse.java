package com.sprint3r.ronin.udemyfeedreader;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class httpClientResponse {

    private httpClientConnect httpclient;
    private JSONObject jsonObject;
    final private String API_URL = "https://www.udemy.com";

    public httpClientResponse() throws IOException {
        httpclient = new httpClientConnect(API_URL+"/api-2.0/courses");
        jsonObject = httpclient.getResult();
    }

    public Object getJSONArrayForCardView(){
        JSONObject cardView = new JSONObject();
        JSONArray results = jsonObject.getJSONArray("results");
        for (int index=0; index<results.length(); index++){
            String dataJson = "{" +
                    "\"title\" : \""+results.getJSONObject(index).get("title")+"\"," +
                    "\"image_240x135\" : \""+results.getJSONObject(index).get("image_240x135")+"\","+
                    "\"price\" : \""+results.getJSONObject(index).get("price")+"\","+
                    "\"url\" : \""+API_URL+results.getJSONObject(index).get("url")+"\""+
                    "}";
            JSONObject tempCardView = new JSONObject(dataJson);
            cardView.append("cardView", tempCardView);
        }
        return cardView.get("cardView");
    }

    public String getNextUrl(){
        return jsonObject.getString("next");
    }

}
