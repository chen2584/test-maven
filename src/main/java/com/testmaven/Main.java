package com.testmaven;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomAlphabetic((5)));
        Chen chen = new Chen();
        chen.HelloWorld();

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if(conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server ... \n");
            StringBuilder sb = new StringBuilder();
            while((output = br.readLine()) != null) {
                System.out.println(output);
                sb.append(output);
            }

            JSONObject jsonArray = new JSONObject(sb.toString());
            System.out.println(jsonArray.get("title"));

            conn.disconnect();
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}