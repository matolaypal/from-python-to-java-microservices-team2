package com.javababok.timecalculator.controller;

import com.javababok.timecalculator.service.APIService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Response;
import spark.Request;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;


/**
 * ORIGIN mean the place, where the shop is.
 * (You can modify here, if you want!)
 */
public class APIController {

    private static APIService apiService;
    private static final String ORIGIN = "Amsterdam";

    /**
     * @param apiService not null
     */
    public APIController(APIService apiService){
        APIController.apiService = apiService;
    }

    /**
     * @param request to the server
     * @param response from the server
     * @return the requested target location from the client
     * @throws IOException when failed or interrupted I/O operations
     * @throws URISyntaxException when the string could not be parsed as an URI reference.
     * @throws JSONException indicates that some exception happened during JSON processing
     */
    public JSONObject location (Request request, Response response) throws IOException, URISyntaxException, JSONException {
        return this.getTimeInMs(request.params(":destination"));
    }

    /**
     * @param destination the place where the customer is
     * @return the final result as JSON
     * @throws IOException when failed or interrupted I/O operations
     * @throws URISyntaxException when the string could not be parsed as an URI reference.
     * @throws JSONException indicates that some exception happened during JSON processing
     */
    public JSONObject getTimeInMs(String destination) throws IOException, URISyntaxException, JSONException {
        JSONObject json = new JSONObject();
        String status = checkStatus(destination);
        // If something went wrong, the time will be 0.
        if(!status.equals("OK")) {
            json.put("time", 0);
            json.put("status", status);
            return json;
        }
        // Here start the work with the jon from Google Maps.
        JSONObject routeDetails = getRouteDetails(destination);
        Integer timeInSec = (Integer) ((JSONObject) routeDetails.get("duration")).get("value");
        // Convert second to millisecond.
        json.put("time", TimeUnit.SECONDS.toMillis(timeInSec));
        json.put("status", status);
        return json;
    }

    /**
     * @param destination the place where the customer is
     * @return the status of searching result
     * @throws IOException when failed or interrupted I/O operations
     * @throws URISyntaxException when the string could not be parsed as an URI reference.
     * @throws JSONException indicates that some exception happened during JSON processing
     */
    public String checkStatus(String destination) throws IOException, URISyntaxException, JSONException {
        // Check the status and expand it, if no OK (or something else).
        JSONObject routeDetails = getRouteDetails(destination);
        String status = routeDetails.get("status").toString();
        switch (status) {
            case "ZERO_RESULTS":
                return "ZERO_RESULTS ERROR: Oversea location!";

            case "NOT_FOUND":
                return "NOT_FOUND ERROR: Place doesn't exist!";
            default:
                return status;
        }
    }

    /**
     * @param destination the place where the customer is
     * @return the base JSON, what contain data for checkStatus() and getTimeInMs()
     * @throws IOException when failed or interrupted I/O operations
     * @throws URISyntaxException when the string could not be parsed as an URI reference.
     * @throws JSONException indicates that some exception happened during JSON processing
     */
    public JSONObject getRouteDetails(String destination) throws IOException, URISyntaxException, JSONException {
        JSONObject jsonObject = new JSONObject(apiService.calcTime(ORIGIN, destination));
        JSONObject routeDetailsList = (JSONObject) ((JSONArray) jsonObject.get("rows")).get(0);
        return (JSONObject) ((JSONArray) routeDetailsList.get("elements")).get(0);
    }

}
