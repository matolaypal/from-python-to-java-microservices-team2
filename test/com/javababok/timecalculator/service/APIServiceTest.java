package com.javababok.timecalculator.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adambodnar on 2017. 01. 04..
 */
public class APIServiceTest {

    APIService apiService = APIService.getInstance();

    @Test
    public void getInstance() throws Exception {
        APIService testApiService = APIService.getInstance();
        assertEquals(testApiService, apiService);
    }

    @Test
    public void calcTimeShouldBuildTheLongGivenURI() throws Exception {
        assertEquals("{\n" +
                "   \"destination_addresses\" : [ \"Berlin, Germany\" ],\n" +
                "   \"origin_addresses\" : [ \"Budapest, Hungary\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"873 km\",\n" +
                "                  \"value\" : 873295\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"8 hours 30 mins\",\n" +
                "                  \"value\" : 30623\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n", apiService.calcTime("Budapest", "Berlin"));
    }

}