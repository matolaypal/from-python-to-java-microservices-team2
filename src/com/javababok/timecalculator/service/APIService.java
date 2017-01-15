package com.javababok.timecalculator.service;
/*
Here get the json from Google and get back for API.
 */
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {

    private static final String API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?";
    private static final String API_KEY = "AIzaSyBCZljDDXlcKS3WICsg0hfndUNF2oLevRY";

    public APIService() {}

    private static APIService INSTANCE;

    /**
     * @return instance of the APIService
     */
    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    /**
     * @param origin the place name, where the shop is
     * @param destination the place name, where the customer is
     * @return the executed URI, as String
     * @throws URISyntaxException, when the string could not be parsed as an URI reference
     * @throws IOException, when failed or interrupted I/O operations
     */
    public String calcTime(String origin, String destination) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL);

        builder.addParameter("units", "metric");
        builder.addParameter("origins", origin);
        builder.addParameter("destinations", destination);
        builder.addParameter("key", API_KEY);

        return execute(builder.build());
    }

    /**
     * @param uri not null
     * @return the executed URI content, as String
     * @throws IOException when failed or interrupted I/O operations
     */
    private String execute(URI uri) throws IOException {
        return Request.Get(uri).execute().returnContent().asString();
    }
}
