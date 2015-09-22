package com.sprint3r.ronin.udemyfeedreader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectByHttp implements UdemyFeedNetworkAdapter{

    private HttpURLConnection urlConnection;
    private JsonObject jsonObj;

    private String url = "https://www.udemy.com/api-2.0/courses/";

    public ConnectByHttp(){
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStreamReader inputReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader bufferReader = new BufferedReader(inputReader);
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = bufferReader.readLine()) != null) {
                total.append(line);
            }
            JsonParser parser = new JsonParser();
            jsonObj = parser.parse(total.toString()).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.setConnectTimeout(3000);
    }

    @Override
    public JsonArray getFeed() {
        return jsonObj.getAsJsonArray("results");
    }
}
