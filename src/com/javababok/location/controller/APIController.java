package com.javababok.location.controller;
import com.javababok.location.service.APIService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;


public class APIController {

    private final APIService apiService;
    private static final String origin  = "Budapest";

    public APIController(APIService apiService){

        this.apiService = apiService;
    }

    public JSONObject location (Request request, Response response) throws IOException, URISyntaxException {

        return apiService.calcTime(origin, request.queryParams("destination"));
    }


}
