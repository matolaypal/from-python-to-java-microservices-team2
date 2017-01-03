package com.javababok.location.service;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URISyntaxException;

public class APIService {

    private static final String API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?";
    private static final String API_KEY = "AIzaSyBCZljDDXlcKS3WICsg0hfndUNF2oLevRY";

    public APIService() {}

    private static APIService INSTANCE;

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    public JSONObject calcTime(String origin, String destination) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL);

        builder.addParameter("units", "metric");
        builder.addParameter("origins", origin);
        builder.addParameter("destinations", destination);
        builder.addParameter("key", API_KEY);

        JSONObject jsonObject = new JSONObject(builder.build());
        return jsonObject;
    }
}
