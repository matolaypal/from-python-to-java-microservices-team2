package com.javababok.timecalculator.controller;

import com.javababok.timecalculator.service.APIService;
import org.apache.http.client.fluent.Response;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;


public class APIControllerTest {


    @Test
    public void getTimeInMsWithCorrectLocation() throws NullPointerException, JSONException, IOException, URISyntaxException {
        APIController apiController = new APIController(APIService.getInstance());
        assertEquals("{\"time\":48279000,\"status\":\"OK\"}", apiController.getTimeInMs("Budapest").toString());

    }

    @Test
    public void wrongLocation() throws NullPointerException, JSONException, IOException, URISyntaxException {
        APIController apiController = new APIController(APIService.getInstance());
        assertEquals("{\"time\":0,\"status\":\"NOT_FOUND ERROR: Place doesn't exist!\"}", apiController.getTimeInMs("sdfghjkertzudfghertz").toString());

    }

    @Test
    public void overseaLocation() throws NullPointerException, JSONException, IOException, URISyntaxException {
        APIController apiController = new APIController(APIService.getInstance());
        assertEquals("{\"time\":0,\"status\":\"ZERO_RESULTS ERROR: Oversea timecalculator!\"}", apiController.getTimeInMs("New York").toString());

    }

    @Test
    public void getRouteDetails() throws Exception {
        APIController apiController = new APIController(APIService.getInstance());
        assertEquals("{\"duration\":{\"text\":\"13 hours 25 mins\",\"value\":48279},\"distance\":{\"text\":\"1,395 km\",\"value\":1395271},\"status\":\"OK\"}", apiController.getRouteDetails("Budapest").toString());

    }


}