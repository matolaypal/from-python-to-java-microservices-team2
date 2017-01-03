package com.javababok.location;

import com.javababok.location.controller.MainController;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        try {
            JSONObject json = MainController.getTimeInMs();
            System.out.println("TEST_RESULT: "+json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
