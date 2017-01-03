package com.javababok.location.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class MainController {

    private static final String API_URL = "http://localhost:60003";

    public static String getJson() throws IOException, URISyntaxException {
        return execute("/api/location");
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(API_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }


    /*public String getResultAsString(JSONObject json) throws JSONException {
        String jsonstring = null;
        JSONArray data = json.getJSONArray("data");
        if(data != null){
            String time[] = new String [data.length()];
            for (int i = 0; i<data.length(); i++){
                if(json[i].equals("duraion")){
                    jsonstring = data.getString(i);
            }
        }
        return jsonstring;
    }

        REPONSE_JSON_OBJECT.getJSONObject("rows").getJSONObject("elements").getJSONArray("duration");*/
}
