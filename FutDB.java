package com.mycompany.futdb;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FutDB {
    private static final String API_URL = "https://futdb.app/api/players/";
    private static final String API_KEY = "3f498d69-5ad6-466a-b441-e20e770053c6";

    public static void main(String[] args) {
        // Set up the API request
        String playerId = "14112"; // FutDB Pele player ID
        String apiUrl = API_URL + playerId;
        Gson gson = new Gson();

        try {
            URL apiUrl2 = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) apiUrl2.openConnection();
            connection.setRequestProperty("X-AUTH-TOKEN", API_KEY); // set API key in request header

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
                JsonObject playerObject = jsonObject.getAsJsonObject("player");
                System.out.println("Name: " + playerObject.get("commonName"));
                System.out.println("Rating: " + playerObject.get("rating"));
                System.out.println("Pace: " + playerObject.get("pace"));
                System.out.println("Shooting: " + playerObject.get("shooting"));
                System.out.println("Passing: " + playerObject.get("passing"));
                System.out.println("Dribbling: " + playerObject.get("dribbling"));
                System.out.println("Defending: " + playerObject.get("defending"));
                System.out.println("Physicality: " + playerObject.get("physicality"));
            }
        } catch (MalformedURLException e) {
            System.out.println("Error: Invalid URL for API request.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: Failed to read API response.");
            e.printStackTrace();
        }
    }
}
