package com.ceiba.completablefuture.util;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CallApi {

    public String callAPI10SecondsForResponse() throws IOException {
        long timeInit = System.currentTimeMillis();
        URL url = new URL("http://moer1.mocklab.io/json/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String response = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            response = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();
        return response;
    }

}
