package com.javababok.location.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;


public class MainController {

    private static final String API_URL = "http://localhost:60003";

    private static JSONObject getRouteDetails() throws IOException, URISyntaxException, JSONException {
        JSONObject jsonObject = new JSONObject(execute("/api/location"));
        JSONObject routeDetailsList = (JSONObject) ((JSONArray) jsonObject.get("rows")).get(0);
        return (JSONObject) ((JSONArray) routeDetailsList.get("elements")).get(0);
    }

    private static String checkStatus() throws IOException, URISyntaxException, JSONException {
        JSONObject routeDetails = getRouteDetails();
        String status = routeDetails.get("status").toString();
        switch (status) {
            case "ZERO_RESULTS":
                return "ZERO_RESULTS ERROR: Oversea location!";

            case "NOT_FOUND":
                return " NOT_FOUND ERROR: Place doesn't exist!";
            default:
                return status;
        }
    }

    public static JSONObject getTimeInMs() throws IOException, URISyntaxException, JSONException {
        JSONObject json = new JSONObject();
        String status = checkStatus();
        if(!status.equals("OK")) {
            json.put("time", 0);
            json.put("status", status);
            return json;
        }
        JSONObject routeDetails = getRouteDetails();
        Integer timeInSec = (Integer) ((JSONObject) routeDetails.get("duration")).get("value");
        json.put("time", TimeUnit.SECONDS.toMillis(timeInSec));
        json.put("status", status);
        return json;
    }

    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(API_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
