package com.javababok.location;

import com.javababok.location.controller.APIController;
import com.javababok.location.service.APIService;

import static spark.Spark.port;
import static spark.Spark.get;

public class TimeGeneratorService {
    private APIController controller;

    public static void main(String[] args) {
        port(60003);

        TimeGeneratorService application = new TimeGeneratorService();
        application.controller = new APIController(APIService.getInstance());

        // --- MAPPING ---
        get("/api/location", application.controller::location);
    }

}
