package com.javababok.location.controller;
import spark.Request;
import spark.Response;


public class APIController {

    private final APIService apiService;

    public APIController(APIService apiService){

        this.apiService = apiService;
    }

    public String location (Request request, Response response){

        return apiService.calcTime(request.queryParams("destination"));
    }


}
