package com.javababok.timecalculator;

import com.javababok.timecalculator.controller.APIController;
import com.javababok.timecalculator.service.APIService;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            APIController c = new APIController(APIService.getInstance());
            System.out.println(c.getTimeInMs("Zagrab"));
        } catch (IOException|URISyntaxException|JSONException e) {
            e.printStackTrace();
        }
    }
}