package com.Code.httpServer.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    private static ObjectMapper myObjectMapper;

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        // Configure the ObjectMapper if needed
        return om;
    }

    public static ObjectMapper getObjectMapper() {
        return myObjectMapper;
    }


}
