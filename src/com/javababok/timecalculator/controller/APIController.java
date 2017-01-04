package com.javababok.timecalculator.controller;

import com.javababok.timecalculator.service.APIService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;


public class APIController {

    private static APIService apiService;
    private static final String ORIGIN = "Amsterdam";

    public APIController(APIService apiService){

        this.apiService = apiService;
    }

    public JSONObject location (spark.Request request, Response response) throws IOException, URISyntaxException, JSONException {
        JSONObject jsonObject = this.getTimeInMs(request.params(":destination"));
        return jsonObject;
    }

    public JSONObject getTimeInMs(String destination) throws IOException, URISyntaxException, JSONException {
        JSONObject json = new JSONObject();
        String status = checkStatus(destination);
        if(!status.equals("OK")) {
            json.put("time", 0);
            json.put("status", status);
            return json;
        }
        JSONObject routeDetails = getRouteDetails(destination);
        Integer timeInSec = (Integer) ((JSONObject) routeDetails.get("duration")).get("value");
        json.put("time", TimeUnit.SECONDS.toMillis(timeInSec));
        json.put("status", status);
        return json;
    }

    private String checkStatus(String destination) throws IOException, URISyntaxException, JSONException {
        JSONObject routeDetails = getRouteDetails(destination);
        String status = routeDetails.get("status").toString();
        switch (status) {
            case "ZERO_RESULTS":
                return "ZERO_RESULTS ERROR: Oversea timecalculator!";

            case "NOT_FOUND":
                return " NOT_FOUND ERROR: Place doesn't exist!";
            default:
                return status;
        }
    }

    public JSONObject getRouteDetails(String destination) throws IOException, URISyntaxException, JSONException {
        JSONObject jsonObject = new JSONObject(this.apiService.calcTime(ORIGIN, destination));
        JSONObject routeDetailsList = (JSONObject) ((JSONArray) jsonObject.get("rows")).get(0);
        return (JSONObject) ((JSONArray) routeDetailsList.get("elements")).get(0);
    }

}
